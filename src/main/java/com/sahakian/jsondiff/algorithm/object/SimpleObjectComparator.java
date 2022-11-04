package com.sahakian.jsondiff.algorithm.object;

import com.google.gson.JsonObject;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleObjectComparator extends AbstractObject  {
    @Override
    public DiffContext diff(JsonObject a, JsonObject b, PathModule pathModule) {
        Set<String> collect = Stream.concat(a.keySet().stream(), b.keySet().stream())
            .collect(Collectors.toSet());

        return diffValueByKey(a, b, collect, pathModule);
    }
}
