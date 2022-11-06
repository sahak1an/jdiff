package com.jdiff.algorithm.object;

import com.google.gson.JsonObject;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;

public class LeftJoinObjectComparator extends AbstractObject {

    @Override
    public DiffContext diff(JsonObject a, JsonObject b, PathModule pathModule) {
        return diffValueByKey(a, b, a.keySet(), pathModule);
    }
}