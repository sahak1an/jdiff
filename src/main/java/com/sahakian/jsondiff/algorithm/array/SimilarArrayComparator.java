package com.sahakian.jsondiff.algorithm.array;

import com.google.gson.JsonArray;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;
import com.sahakian.jsondiff.model.SingleNodeDifference;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SimilarArrayComparator extends AbstractArray {
    private final boolean USABLE = false;
    private final boolean USED = true;

    @Override
    public DiffContext diffArray(JsonArray a, JsonArray b, PathModule pathModule) {
        DiffContext diffContext;
        if(a.size() <= b.size()){
            diffContext = diff(a, b, pathModule);
        } else {
            exchangeLeftAndRightPath(pathModule);
            diffContext = diff(b, a, pathModule);
            exchangeLeftAndRightPath(pathModule);
            exchangeResult(diffContext);
        }
        return diffContext;
    }

    private void exchangeResult(DiffContext diffContext) {
        List<SingleNodeDifference> singleNodeDifferences = diffContext.getDiffResultModels();
        for(SingleNodeDifference singleNodeDifference : singleNodeDifferences){
            exchangePathAndResult(singleNodeDifference);
        }
    }

    private void exchangePathAndResult(SingleNodeDifference singleNodeDifference) {
        String tempStringA = singleNodeDifference.getLeftPath();
        Object tempLeft = singleNodeDifference.getLeft();
        singleNodeDifference.setLeftPath(singleNodeDifference.getRightPath());
        singleNodeDifference.setRightPath(tempStringA);
        singleNodeDifference.setLeft(singleNodeDifference.getRight());
        singleNodeDifference.setRight(tempLeft);
    }

    private void exchangeLeftAndRightPath(PathModule pathModule) {
        LinkedList<String> tempA = pathModule.getLeftPath();
        pathModule.setLeftPath(pathModule.getRightPath());
        pathModule.setRightPath(tempA);
    }

    DiffContext diff(JsonArray a, JsonArray b, PathModule pathModule ) {
        int rowLength = a.size();
        int lineLength = b.size();
        int[][] similarMatrix = new int[rowLength][lineLength];
        boolean []row = new boolean[rowLength];
        boolean []line = new boolean[lineLength];
        for (int i = 0; i < rowLength; i++) {
            pathModule.addLeftPath(constructArrayPath(i));
            constructSimilarMatrix(a, b, i, pathModule, similarMatrix, row, line);
            pathModule.removeLastLeftPath();
        }
        return obtainDiffResult(a, b, pathModule, row, line, similarMatrix);
    }

    private DiffContext obtainDiffResult(JsonArray a, JsonArray b, PathModule pathModule, boolean[] row, boolean[] line, int[][] similarMatrix) {
        DiffContext arrayDiffContext = new DiffContext();
        obtainModifyDiffResult(a,b,pathModule,row,line,similarMatrix, arrayDiffContext);
        obtainAddDiffResult(b,pathModule,line, arrayDiffContext);

        return arrayDiffContext;
    }

    private void obtainAddDiffResult(JsonArray b, PathModule pathModule, boolean[] line, DiffContext arrayDiffContext) {
        for (int j = 0; j < line.length; j++) {
            if (line[j] == USED) {
                continue;
            }
            DiffContext addOrDeleteDiffContext = constructAddContext(b, j, pathModule);
            parentContextAddChildContext(arrayDiffContext, addOrDeleteDiffContext);
        }
    }

    private void obtainModifyDiffResult(JsonArray a, JsonArray b, PathModule pathModule, boolean[] row, boolean[] line, int[][] similarMatrix, DiffContext arrayDiffContext) {
        int counts = 0;
        for (boolean value : row) {
            if (Objects.equals(USABLE, value)) {
                counts++;
            }
        }
        for (int n = 0; n < counts; n++) {
            int bestLineIndex = 0;
            int bestRowIndex = 0;
            int minDiffPair = Integer.MAX_VALUE;
            for (int i = 0; i < row.length; i++) {
                for (int j = 0; j < line.length; j++) {
                    if (row[i] == USED || line[j] == USED) {
                        continue;
                    }
                    if (similarMatrix[i][j] < minDiffPair) {
                        bestRowIndex = i;
                        bestLineIndex = j;
                        minDiffPair = similarMatrix[i][j];
                    }
                }
            }
            DiffContext modifyDiffContext = constructModifyContext(a, b, bestRowIndex, bestLineIndex, pathModule);
            row[bestRowIndex] = USED;
            line[bestLineIndex] = USED;
            parentContextAddChildContext(arrayDiffContext, modifyDiffContext);
        }
    }

    private DiffContext constructAddContext(JsonArray b, int index, PathModule pathModule) {
        pathModule.addAllpath(constructArrayPath(index));
        DiffContext diffContext = diffElement(null, b.get(index), pathModule);
        pathModule.removeAllLastPath();
        return diffContext;
    }
    private DiffContext constructModifyContext(JsonArray a, JsonArray b, int i, int bestLineIndex, PathModule pathModule) {
        pathModule.addLeftPath(constructArrayPath(i));
        pathModule.addRightPath(constructArrayPath(bestLineIndex));
        DiffContext diffContext = diffElement(a.get(i), b.get(bestLineIndex), pathModule);
        pathModule.removeAllLastPath();
        return diffContext;
    }
    private void constructSimilarMatrix(JsonArray arrayA, JsonArray arrayB, int  rowIndex, PathModule pathModule, int [][]similarArray, boolean[] row, boolean[] line) {
        if(rowIndex < 0 || rowIndex >= arrayB.size()){
            throw new RuntimeException(
                "The index number input parameter exceeds the length of the array. Index number：" + rowIndex +" array B:" + arrayB);
        }

        for (int j = 0; j < arrayB.size(); j++) {
            if (line[j] == USABLE) {
                pathModule.addRightPath(constructArrayPath(j));
                DiffContext diffContext = diffElement(arrayA.get(rowIndex), arrayB.get(j), pathModule);
                pathModule.removeLastRightPath();
                if (diffContext.isSame()) {
                    row[rowIndex] = USED;
                    line[j] = USED;
                    return;
                } else if(existSpecialPath(diffContext.getSpecialPathResult())){
                    similarArray[rowIndex][j] = 0 ;
                }  else {
                    similarArray[rowIndex][j] = diffContext.getDiffResultModels().size();
                }
            }
        }
    }

    private boolean existSpecialPath(LinkedList<String> specialPathResult) {
        return specialPathResult != null && !specialPathResult.isEmpty();
    }
}
