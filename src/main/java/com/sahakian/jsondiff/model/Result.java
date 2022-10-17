package com.sahakian.jsondiff.model;

import java.io.Serializable;

public class Result implements Serializable {
    private String leftPath;
    private String rightPath;
    private Object left;
    private Object right;
    private String diffType;

    public String getLeftPath() {
        return leftPath;
    }

    public String getRightPath() {
        return rightPath;
    }

    public void setLeftPath(String leftPath) {
        this.leftPath = leftPath;
    }

    public void setRightPath(String rightPath) {
        this.rightPath = rightPath;
    }

    public Object getLeft() {
        return left;
    }

    public void setLeft(Object left) {
        this.left = left;
    }

    public Object getRight() {
        return right;
    }

    public void setRight(Object right) {
        this.right = right;
    }

    public String getDiffType() {
        return diffType;
    }

    public void setDiffType(String diffType) {
        this.diffType = diffType;
    }
}
