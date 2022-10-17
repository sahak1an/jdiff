package com.sahakian.jsondiff.algorithm.primitive;

import java.util.ArrayList;

import com.google.common.base.Joiner;
import com.google.gson.JsonPrimitive;
import com.sahakian.jsondiff.algorithm.AbstractPrimitiveAndOther;
import com.sahakian.jsondiff.model.Constants;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;
import com.sahakian.jsondiff.model.SingleNodeDifference;

public class DefaultPrimitiveComparator extends AbstractPrimitiveAndOther implements PrimitiveComparator {
    @Override
    public DiffContext diff(JsonPrimitive a, JsonPrimitive b, PathModule pathModule){
        DiffContext primitiveDiffContext = new DiffContext();
        if(Constants.DIFFERENT == a.equals(b)) {
            var singleNodeDifferences = new ArrayList<SingleNodeDifference>();
            singleNodeDifferences.add(new SingleNodeDifference(Joiner.on(Constants.MERGE_PATH).join(pathModule.getLeftPath()), Joiner.on(Constants.MERGE_PATH).join(pathModule.getRightPath()), jsonElement2Str(a), jsonElement2Str(b)));
            primitiveDiffContext.setDiffResultModels(singleNodeDifferences);
            primitiveDiffContext.setSame(Constants.DIFFERENT);
        }
        return primitiveDiffContext;
    }

}
