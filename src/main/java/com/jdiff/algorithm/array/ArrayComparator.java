package com.jdiff.algorithm.array;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.jdiff.algorithm.Comparator;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;
import com.jdiff.algorithm.AlgorithmModule;

public interface ArrayComparator extends Comparator {
    DiffContext diffArray(JsonArray a, JsonArray b, PathModule pathModule);

    DiffContext diffElement(JsonElement a, JsonElement b, PathModule pathModule);

    void constructAlgorithmModule(AlgorithmModule algorithmModule);
}
