package com.sahakian.jsondiff.model;

import java.util.LinkedList;
import java.util.List;

public class PathModule {
    private LinkedList<String> leftPath;
    private LinkedList<String> rightPath;
    private List<String> specialPath;
    private List<String> noisePahList;

    public PathModule() {
        this.leftPath = new LinkedList<>();
        this.rightPath = new LinkedList<>();
    }

    public PathModule(List<String> noisePahList) {
        this.leftPath = new LinkedList<>();
        this.rightPath = new LinkedList<>();
        this.noisePahList = noisePahList;
    }

    public PathModule(List<String> noisePahList, List<String> specialPath) {
        this.leftPath = new LinkedList<>();
        this.rightPath = new LinkedList<>();
        this.noisePahList = noisePahList;
        this.specialPath = specialPath;
    }

    public List<String> getNoisePahList() {
        return noisePahList;
    }

    public void setNoisePahList(List<String> noisePahList) {
        this.noisePahList = noisePahList;
    }

    public List<String> getSpecialPath() {
        return specialPath;
    }

    public void setSpecialPath(LinkedList<String> specialPath) {
        this.specialPath = specialPath;
    }

    public LinkedList<String> getLeftPath() {
        return leftPath;
    }

    public void setLeftPath(LinkedList<String> leftPath) {
        this.leftPath = leftPath;
    }

    public LinkedList<String> getRightPath() {
        return rightPath;
    }

    public void setRightPath(LinkedList<String> rightPath) {
        this.rightPath = rightPath;
    }

    public void addAllpath(String lastPath) {
        leftPath.add(lastPath);
        rightPath.add(lastPath);
    }

    public void addLeftPath(String lastPath) {
        leftPath.add(lastPath);
    }

    public void addRightPath(String lastPath) {
        rightPath.add(lastPath);
    }

    public void removeAllLastPath() {
        leftPath.removeLast();
        rightPath.removeLast();
    }

    public void removeLastLeftPath() {
        leftPath.removeLast();
    }

    public void removeLastRightPath() {
        rightPath.removeLast();
    }
}

