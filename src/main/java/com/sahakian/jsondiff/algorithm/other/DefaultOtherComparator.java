package com.sahakian.jsondiff.algorithm.other;

import static com.sahakian.jsondiff.model.Constants.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.sahakian.jsondiff.algorithm.AbstractPrimitiveAndOther;
import com.sahakian.jsondiff.model.Constants;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;
import com.sahakian.jsondiff.model.SingleNodeDifference;

public class DefaultOtherComparator extends AbstractPrimitiveAndOther implements OtherComparator {

    @Override
    public DiffContext diff(JsonElement a, JsonElement b, PathModule pathModule) {
        DiffContext otherDiffContext = new DiffContext(DIFFERENT);

        String left = String.join(MERGE_PATH, pathModule.getLeftPath());
        String right = String.join(MERGE_PATH, pathModule.getRightPath());

        SingleNodeDifference singleNodeDiff = new SingleNodeDifference(left, right, jsonElement2Str(a), jsonElement2Str(b));

        otherDiffContext.setDiffResultModels(List.of(singleNodeDiff));
        return otherDiffContext;
    }
}
