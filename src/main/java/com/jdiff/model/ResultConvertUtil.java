package com.jdiff.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultConvertUtil {
    public static final String OBJECT_NULL = null;
    public static final String TYPE_MODIFY = "MODIFY";
    public static final String TYPE_ADD = "ADD";
    public static final String TYPE_DELETE = "DELETE";

    public static List<Result> constructResult(DiffContext diffContext) {
        List<Result> list = new ArrayList<>();
        for (SingleNodeDifference resultModel : diffContext.getDiffResultModels()) {
            Result printModel = convert(resultModel);

            boolean leftAndRightBothNull = (Objects.equals(OBJECT_NULL, resultModel.getLeft()))
                    && Objects.equals(OBJECT_NULL, resultModel.getRight());
            if (leftAndRightBothNull) {
                printModel.setDiffType(TYPE_MODIFY);
            } else if (Objects.equals(OBJECT_NULL, resultModel.getLeft())) {
                printModel.setDiffType(TYPE_ADD);
                printModel.setLeftPath(null);
            } else if (Objects.equals(OBJECT_NULL, resultModel.getRight())) {
                printModel.setDiffType(TYPE_DELETE);
                printModel.setRightPath(null);
            } else {
                printModel.setDiffType(TYPE_MODIFY);
            }
            list.add(printModel);
        }
        return list;
    }

    private static Result convert(SingleNodeDifference resultModel) {
        Result printModel = new Result();
        printModel.setLeft(resultModel.getLeft());
        printModel.setRight(resultModel.getRight());
        printModel.setLeftPath(resultModel.getLeftPath());
        printModel.setRightPath(resultModel.getRightPath());
        return printModel;
    }
}
