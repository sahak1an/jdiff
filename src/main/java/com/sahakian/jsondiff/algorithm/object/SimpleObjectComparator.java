package com.sahakian.jsondiff.algorithm.object;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

import java.util.Set;

public class SimpleObjectComparator extends AbstractObject  {
    @Override
    public DiffContext diff(JsonObject a, JsonObject b, PathModule pathModule) {
        Set<String> unionSet = Sets.union(a.keySet(), b.keySet());
        return diffValueByKey(a, b, unionSet, pathModule);
    }
}
