package com.jdiff.algorithm.nulls;

import com.google.gson.JsonNull;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;

public class DefaultNullComparator implements NullComparator {
    @Override
    public DiffContext diff(JsonNull a, JsonNull b, PathModule pathModule) {
        return new DiffContext();
    }
}