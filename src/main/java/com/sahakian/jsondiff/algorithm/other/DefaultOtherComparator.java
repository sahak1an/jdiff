package com.sahakian.jsondiff.algorithm.other;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.gson.JsonElement;
import com.sahakian.jsondiff.algorithm.AbstractPrimitiveAndOther;
import com.sahakian.jsondiff.model.Constants;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;
import com.sahakian.jsondiff.model.SingleNodeDifference;

public class DefaultOtherComparator extends AbstractPrimitiveAndOther implements OtherComparator {

    @Override
    public DiffContext diff(JsonElement a, JsonElement b, PathModule pathModule){
        DiffContext otherDiffContext = new DiffContext(Constants.DIFFERENT);
        List<SingleNodeDifference> singleNodeDifferences = new ArrayList<>();
        singleNodeDifferences.add(new SingleNodeDifference(Joiner.on(Constants.MERGE_PATH).join(pathModule.getLeftPath()), Joiner.on(Constants.MERGE_PATH).join(pathModule.getRightPath()), jsonElement2Str(a), jsonElement2Str(b)));
        otherDiffContext.setDiffResultModels(singleNodeDifferences);
        return otherDiffContext;
    }
}
