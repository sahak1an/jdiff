package com.jdiff.algorithm;

import com.google.gson.JsonElement;
import com.jdiff.model.Constants;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;
import com.jdiff.model.SingleNodeDifference;

public abstract class AbstractObjectAndArray {
    protected AlgorithmModule algorithmModule;

    public DiffContext diffElement(JsonElement a, JsonElement b, PathModule pathModule) {
        return algorithmModule.diffElement(a, b, pathModule);
    }

    public void constructAlgorithmModule(AlgorithmModule algorithmModule) {
        this.algorithmModule = algorithmModule;
    }

    public void parentContextAddChildContext(DiffContext parentResult, DiffContext childResult) {
        if (childResult.isSame() == Constants.DIFFERENT) {
            for (SingleNodeDifference singleNodeDifference : childResult.getDiffResultModels()) {
                parentResult.getDiffResultModels().add(singleNodeDifference);
            }
            parentResult.setSame(false);
        }
    }
}