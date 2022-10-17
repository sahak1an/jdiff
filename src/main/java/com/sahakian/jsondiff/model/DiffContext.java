package com.sahakian.jsondiff.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DiffContext {
    private boolean isSame;
    private List<SingleNodeDifference> singleNodeDifferences;
    private LinkedList<String> specialPathResult;

    public DiffContext(boolean isSame) {
        this.isSame = isSame;
        this.singleNodeDifferences = new ArrayList<>();
    }

    public boolean isSame() {
        return isSame;
    }

    public void setSame(boolean same) {
        isSame = same;
    }

    public List<SingleNodeDifference> getDiffResultModels() {
        return singleNodeDifferences;
    }

    public void setDiffResultModels(List<SingleNodeDifference> singleNodeDifferences) {
        this.singleNodeDifferences = singleNodeDifferences;
    }

    public DiffContext() {
        this.isSame = true;
        this.singleNodeDifferences = new ArrayList<>();
    }

    public LinkedList<String> getSpecialPathResult() {
        return specialPathResult;
    }

    public void setSpecialPathResult(LinkedList<String> specialPathResult) {
        this.specialPathResult = specialPathResult;
    }
}
