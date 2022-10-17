package com.sahakian.jsondiff.algorithm.object;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sahakian.jsondiff.algorithm.AlgorithmModule;
import com.sahakian.jsondiff.algorithm.Comparator;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

public interface ObjectComparator extends Comparator {
    DiffContext diff(JsonObject a, JsonObject b, PathModule pathModule);
    DiffContext diffElement(JsonElement a, JsonElement b, PathModule pathModule);
    void constructAlgorithmModule(AlgorithmModule algorithmModule);
}