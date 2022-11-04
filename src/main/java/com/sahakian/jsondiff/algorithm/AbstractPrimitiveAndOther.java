package com.sahakian.jsondiff.algorithm;

import java.util.Optional;

import com.google.gson.JsonElement;

public abstract class AbstractPrimitiveAndOther {
    protected static String jsonElement2Str(JsonElement element){
     return Optional.ofNullable(element)
         .map(JsonElement::toString)
         .orElse(null);
    }
}
