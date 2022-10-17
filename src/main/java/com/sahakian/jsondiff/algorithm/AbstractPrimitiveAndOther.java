package com.sahakian.jsondiff.algorithm;

import com.google.gson.JsonElement;

public abstract class AbstractPrimitiveAndOther {
    protected static String jsonElement2Str(JsonElement element){
        //该对象不存在的情况
        if(element == null){
            return null;
        } else if (element.isJsonObject()) {
            return "{Omitted internal character}";
        } else if (element.isJsonArray()) {
            return "[Omitted internal elements]";
        } else if (element.isJsonPrimitive()) {
            return element.getAsJsonPrimitive().getAsString();
        } else if (element.isJsonNull()) {
            return "null";
        }else{
            throw new RuntimeException();
        }
    }
}
