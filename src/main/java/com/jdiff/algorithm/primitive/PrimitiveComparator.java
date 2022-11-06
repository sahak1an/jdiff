package com.jdiff.algorithm.primitive;

import com.google.gson.JsonPrimitive;
import com.jdiff.algorithm.Comparator;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;

public interface PrimitiveComparator extends Comparator {
    DiffContext diff(JsonPrimitive a, JsonPrimitive b, PathModule pathModule);
}
