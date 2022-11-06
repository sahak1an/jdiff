package com.jdiff.algorithm;

import com.google.gson.JsonElement;

import java.util.Optional;

public abstract class AbstractPrimitiveAndOther {
    protected static String jsonElement2Str(JsonElement element) {
        return Optional.ofNullable(element)
                .map(JsonElement::toString)
                .orElse(null);
    }
}
