package com.jdiff.algorithm.primitive;

import com.google.gson.JsonPrimitive;
import com.jdiff.algorithm.AbstractPrimitiveAndOther;
import com.jdiff.model.Constants;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;
import com.jdiff.model.SingleNodeDifference;

import java.util.ArrayList;

public class DefaultPrimitiveComparator extends AbstractPrimitiveAndOther implements PrimitiveComparator {
    @Override
    public DiffContext diff(JsonPrimitive a, JsonPrimitive b, PathModule pathModule) {
        DiffContext primitiveDiffContext = new DiffContext();
        if (Constants.DIFFERENT == a.equals(b)) {
            var singleNodeDifferences = new ArrayList<SingleNodeDifference>();

            String left = String.join(Constants.MERGE_PATH, pathModule.getLeftPath());
            String right = String.join(Constants.MERGE_PATH, pathModule.getRightPath());
            singleNodeDifferences.add(new SingleNodeDifference(left, right, jsonElement2Str(a), jsonElement2Str(b)));
            primitiveDiffContext.setDiffResultModels(singleNodeDifferences);
            primitiveDiffContext.setSame(Constants.DIFFERENT);
        }
        return primitiveDiffContext;
    }

}
