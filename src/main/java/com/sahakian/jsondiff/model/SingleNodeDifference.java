package com.sahakian.jsondiff.model;

public class SingleNodeDifference {
    private String leftPath;
    private String rightPath;
    private Object left;
    private Object right;

    public SingleNodeDifference(String leftPath, String rightPath, Object left, Object right) {
        this.leftPath = leftPath;
        this.rightPath = rightPath;
        this.left = left;
        this.right = right;
    }

    public String getLeftPath() {
        return leftPath;
    }

    public void setLeftPath(String leftPath) {
        this.leftPath = leftPath;
    }

    public String getRightPath() {
        return rightPath;
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
}