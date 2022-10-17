package com.sahakian.jsondiff.algorithm.primitive;

import com.google.gson.JsonPrimitive;
import com.sahakian.jsondiff.algorithm.Comparator;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

public interface PrimitiveComparator extends Comparator {
    DiffContext diff(JsonPrimitive a, JsonPrimitive b, PathModule pathModule);
}
