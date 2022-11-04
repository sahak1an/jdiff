package com.sahakian.jsondiff.algorithm.nulls;

import com.google.gson.JsonNull;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

public class DefaultNullComparator implements NullComparator {
    @Override
    public DiffContext diff(JsonNull a, JsonNull b, PathModule pathModule){
        return new DiffContext();
    }
}