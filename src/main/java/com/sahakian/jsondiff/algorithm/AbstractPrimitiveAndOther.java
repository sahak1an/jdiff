package com.sahakian.jsondiff.algorithm;

import java.util.Optional;

import com.google.gson.JsonElement;

public abstract class AbstractPrimitiveAndOther {
    protected static String jsonElement2Str(JsonElement element){
     // return Optional.ofNullable(element)
     //     .map(JsonElement::getAsString)
     //     .orElseThrow();
        if(element == null){
            return null;
        } else if (element.isJsonObject()) {
            //element.getAsString()
            return element.toString();
        } else if (element.isJsonArray()) {
            return element.toString();
           // return "[Omitted internal elements]";
        } else if (element.isJsonPrimitive()) {
            return element.getAsJsonPrimitive().getAsString();
        } else if (element.isJsonNull()) {
            return "null";
        }else{
            throw new RuntimeException();
        }
    }
}
