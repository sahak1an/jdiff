package com.sahakian.jsondiff.algorithm.array;

import com.sahakian.jsondiff.algorithm.AbstractObjectAndArray;

public abstract class AbstractArray extends AbstractObjectAndArray implements ArrayComparator {
    protected String  constructArrayPath(Integer i){
        if(i == null || i < 0 ){
            throw new RuntimeException("The array index number input parameter is empty or negative:" + i);
        }
        return "[" + i + "]";
    }
}
