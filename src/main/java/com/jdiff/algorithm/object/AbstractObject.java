package com.jdiff.algorithm.object;

import com.google.gson.JsonObject;
import com.jdiff.algorithm.AbstractObjectAndArray;
import com.jdiff.model.Constants;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractObject extends AbstractObjectAndArray implements ObjectComparator {

    protected DiffContext diffValueByKey(JsonObject a, JsonObject b, Set<String> keySet, PathModule pathModule) {
        DiffContext objectDiffContext = new DiffContext();
        LinkedList<String> specialPathResult = new LinkedList<>();
        for (String key : keySet) {
            pathModule.addAllPath(key);
            if (!needDiff(pathModule.getNoisePahList(), pathModule.getLeftPath())) {
                pathModule.removeAllLastPath();
                continue;
            }
            DiffContext diffContext = diffElement(a.get(key), b.get(key), pathModule);
            parentContextAddChildContext(objectDiffContext, diffContext);
            specialPathHandle(diffContext.isSame(), specialPathResult, pathModule);
            pathModule.removeAllLastPath();

        }
        objectDiffContext.setSpecialPathResult(specialPathResult);
        return objectDiffContext;
    }

    private void specialPathHandle(boolean isSame, LinkedList<String> specialPathResult, PathModule pathModule) {
        if (!isSame) {
            return;
        }
        String specialPath = getSpecialPath(pathModule);
        if (existPath(specialPath)) {
            specialPathResult.add(specialPath);
        }
    }

    private boolean existPath(String specialPath) {
        return specialPath != null;
    }

    protected String getSpecialPath(PathModule pathModule) {
        if (pathModule == null || pathModule.getSpecialPath() == null || pathModule.getSpecialPath().isEmpty()) {
            return null;
        }
        String currentPath = listJoin(pathModule.getLeftPath());
        if (pathModule.getSpecialPath().contains(currentPath)) {
            return currentPath;
        }
        return null;
    }

    protected boolean needDiff(List<String> noisePahList, LinkedList<String> pathList) {
        if (noisePahList == null || pathList == null || noisePahList.isEmpty() || pathList.isEmpty()) {
            return true;
        }
        String path = listJoin(pathList);

        return !noisePahList.contains(path);
    }

    protected String listJoin(LinkedList<String> path) {
        if (path == null) {
            throw new RuntimeException("Path is null");
        }
        return path.stream()
                .filter(e -> e.charAt(0) != '[')
                .collect(Collectors.joining(Constants.MERGE_PATH));
    }
}