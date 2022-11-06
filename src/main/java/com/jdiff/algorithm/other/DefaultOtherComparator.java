package com.jdiff.algorithm.other;

import com.google.gson.JsonElement;
import com.jdiff.algorithm.AbstractPrimitiveAndOther;
import com.jdiff.model.Constants;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;
import com.jdiff.model.SingleNodeDifference;

import java.util.List;

public class DefaultOtherComparator extends AbstractPrimitiveAndOther implements OtherComparator {

    @Override
    public DiffContext diff(JsonElement a, JsonElement b, PathModule pathModule) {
        DiffContext otherDiffContext = new DiffContext(Constants.DIFFERENT);

        String left = String.join(Constants.MERGE_PATH, pathModule.getLeftPath());
        String right = String.join(Constants.MERGE_PATH, pathModule.getRightPath());

        SingleNodeDifference singleNodeDiff = new SingleNodeDifference(left, right, jsonElement2Str(a), jsonElement2Str(b));

        otherDiffContext.setDiffResultModels(List.of(singleNodeDiff));
        return otherDiffContext;
    }
}
