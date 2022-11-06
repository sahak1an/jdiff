package com.jdiff.algorithm;

import com.google.gson.*;
import com.jdiff.algorithm.array.ArrayComparator;
import com.jdiff.algorithm.nulls.NullComparator;
import com.jdiff.algorithm.object.ObjectComparator;
import com.jdiff.algorithm.other.OtherComparator;
import com.jdiff.algorithm.primitive.PrimitiveComparator;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;

public class AlgorithmModule {
    protected final ObjectComparator objectAlgorithm;
    protected final ArrayComparator arrayAlgorithm;
    protected final PrimitiveComparator primitiveComparator;
    protected final NullComparator nullComparator;
    protected final OtherComparator otherComparator;

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
            return objectAlgorithm.diff((JsonObject) a, (JsonObject) b, pathModule);
        } else if (a instanceof JsonArray && b instanceof JsonArray) {
            return arrayAlgorithm.diffArray((JsonArray) a, (JsonArray) b, pathModule);
        } else if (a instanceof JsonPrimitive && b instanceof JsonPrimitive) {
            return primitiveComparator.diff((JsonPrimitive) a, (JsonPrimitive) b, pathModule);
        } else if (a instanceof JsonNull && b instanceof JsonNull) {
            return nullComparator.diff((JsonNull) a, (JsonNull) b, pathModule);
        } else {
            return otherComparator.diff(a, b, pathModule);
        }
    }

    public ArrayComparator getArrayAlgorithm() {
        return arrayAlgorithm;
    }
}
