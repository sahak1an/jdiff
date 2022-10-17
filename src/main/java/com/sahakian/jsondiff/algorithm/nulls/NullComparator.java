package com.sahakian.jsondiff.algorithm.nulls;

import com.google.gson.JsonNull;
import com.sahakian.jsondiff.algorithm.Comparator;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

public interface NullComparator extends Comparator {

    DiffContext diff(JsonNull a, JsonNull b, PathModule pathModule);
}