package com.sahakian.jsondiff.algorithm.other;

import com.google.gson.JsonElement;
import com.sahakian.jsondiff.algorithm.Comparator;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

public interface OtherComparator extends Comparator {
    DiffContext diff(JsonElement a, JsonElement b, PathModule pathModule);
}
