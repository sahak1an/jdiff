package com.sahakian.jsondiff.algorithm.array;

import com.google.gson.JsonArray;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;

public class SimpleArrayComparator extends AbstractArray  {
    @Override
    public DiffContext diffArray(JsonArray a, JsonArray b, PathModule pathModule) {
        DiffContext arrayDiffContext = new DiffContext();
        int maxLength = Math.max(a.size(), b.size());
        //根据数组a和b长度的大的值进行遍历
        for (int i = 0; i < maxLength; i++) {
            pathModule.addAllpath(constructArrayPath(i));
            DiffContext diffContext =  generateDiffResult(a,b,i,pathModule);
            parentContextAddChildContext(arrayDiffContext, diffContext);
        }
        return arrayDiffContext;
    }

    private DiffContext generateDiffResult(JsonArray a, JsonArray b, int i, PathModule pathModule) {
        if(i >= a.size() && i >= b.size()){
            throw new RuntimeException("数组索引号入参超过数组长度。 索引号:" + i + " 数组a:" + a + "数组b：" + b);
        }
        DiffContext diffContext;
        if(i < a.size() && i < b.size()){
            diffContext = diffElement(a.get(i), b.get(i), pathModule);
        }else if (i >= a.size()){
            diffContext = diffElement(null, b.get(i), pathModule);
        }else{
            diffContext = diffElement(a.get(i), null, pathModule);
        }
        return diffContext;
    }
}

