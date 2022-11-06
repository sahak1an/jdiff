package com.jdiff.algorithm.array;

import com.google.gson.JsonArray;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;

public class SimpleArrayComparator extends AbstractArray {
    @Override
    public DiffContext diffArray(JsonArray a, JsonArray b, PathModule pathModule) {
        DiffContext arrayDiffContext = new DiffContext();
        int maxLength = Math.max(a.size(), b.size());

        for (int i = 0; i < maxLength; i++) {
            pathModule.addAllPath(constructArrayPath(i));
            DiffContext diffContext = generateDiffResult(a, b, i, pathModule);
            parentContextAddChildContext(arrayDiffContext, diffContext);
        }
        return arrayDiffContext;
    }

    private DiffContext generateDiffResult(JsonArray a, JsonArray b, int i, PathModule pathModule) {
        if (i >= a.size() && i >= b.size()) {
            throw new RuntimeException(
                    "The input parameter of the array index number exceeds the length of the array. Index number:" + i + " array a:" + a + "array b：" + b);
        }
        DiffContext diffContext;
        if (i < a.size() && i < b.size()) {
            diffContext = diffElement(a.get(i), b.get(i), pathModule);
        } else if (i >= a.size()) {
            diffContext = diffElement(null, b.get(i), pathModule);
        } else {
            diffContext = diffElement(a.get(i), null, pathModule);
        }
        return diffContext;
    }
}

