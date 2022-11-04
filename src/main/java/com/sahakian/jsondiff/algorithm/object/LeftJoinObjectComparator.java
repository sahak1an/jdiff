package com.sahakian.jsondiff.algorithm.object;

import com.google.gson.JsonObject;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

public class LeftJoinObjectComparator extends AbstractObject  {

    @Override
    public DiffContext diff(JsonObject a, JsonObject b, PathModule pathModule){
        return diffValueByKey(a, b, a.keySet(), pathModule);
    }
}