package com.jdiff.algorithm.other;

import com.google.gson.JsonElement;
import com.jdiff.algorithm.Comparator;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;

public interface OtherComparator extends Comparator {
    DiffContext diff(JsonElement a, JsonElement b, PathModule pathModule);
}
