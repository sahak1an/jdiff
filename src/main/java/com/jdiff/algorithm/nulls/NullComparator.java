package com.jdiff.algorithm.nulls;

import com.google.gson.JsonNull;
import com.jdiff.algorithm.Comparator;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;

public interface NullComparator extends Comparator {

    DiffContext diff(JsonNull a, JsonNull b, PathModule pathModule);
}