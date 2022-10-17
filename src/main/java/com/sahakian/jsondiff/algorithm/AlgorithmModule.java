package com.sahakian.jsondiff.algorithm;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.sahakian.jsondiff.algorithm.array.ArrayComparator;
import com.sahakian.jsondiff.algorithm.nulls.NullComparator;
import com.sahakian.jsondiff.algorithm.object.ObjectComparator;
import com.sahakian.jsondiff.algorithm.other.OtherComparator;
import com.sahakian.jsondiff.algorithm.primitive.PrimitiveComparator;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

public class AlgorithmModule{
    protected ObjectComparator objectAlgorithm;
    protected ArrayComparator arrayAlgorithm;
    protected PrimitiveComparator primitiveComparator;
    protected NullComparator nullComparator;
    protected OtherComparator otherComparator;

    public AlgorithmModule(ObjectComparator objectAlgorithm, ArrayComparator arrayAlgorithm,
                           PrimitiveComparator primitiveComparator, NullComparator nullComparator, OtherComparator otherComparator) {
        this.arrayAlgorithm = arrayAlgorithm;
        this.objectAlgorithm = objectAlgorithm;
        this.primitiveComparator = primitiveComparator;
        this.nullComparator = nullComparator;
        this.otherComparator = otherComparator;
        objectAlgorithm.constructAlgorithmModule(this);
        arrayAlgorithm.constructAlgorithmModule(this);
    }

    public DiffContext diffElement(JsonElement a, JsonElement b, PathModule pathModule) {
        if (a instanceof JsonObject && b instanceof JsonObject) {
            return objectAlgorithm.diff( (JsonObject) a, (JsonObject) b, pathModule);
        } else if (a instanceof JsonArray && b instanceof JsonArray) {
            return arrayAlgorithm.diffArray((JsonArray) a, (JsonArray) b, pathModule);
        } else if (a instanceof JsonPrimitive && b instanceof JsonPrimitive) {
            return  primitiveComparator.diff((JsonPrimitive) a, (JsonPrimitive) b, pathModule);
        } else if (a instanceof JsonNull && b instanceof JsonNull) {
            return nullComparator.diff((JsonNull) a, (JsonNull) b, pathModule);
        } else  {
            return otherComparator.diff(a, b, pathModule);
        }
    }

    public ArrayComparator getArrayAlgorithm() {
        return arrayAlgorithm;
    }

}
