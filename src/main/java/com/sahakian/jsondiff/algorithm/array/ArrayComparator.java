package com.sahakian.jsondiff.algorithm.array;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.sahakian.jsondiff.algorithm.AlgorithmModule;
import com.sahakian.jsondiff.algorithm.Comparator;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

public interface ArrayComparator extends Comparator {
    DiffContext diffArray(JsonArray a, JsonArray b, PathModule pathModule) ;

    DiffContext diffElement(JsonElement a, JsonElement b, PathModule pathModule);

    void constructAlgorithmModule(AlgorithmModule algorithmModule);
}
