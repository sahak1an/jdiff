package com.sahakian.jsondiff;

import com.sahakian.jsondiff.algorithm.AlgorithmModule;
import com.sahakian.jsondiff.algorithm.array.SimilarArrayComparator;
import com.sahakian.jsondiff.algorithm.array.SimpleArrayComparator;
import com.sahakian.jsondiff.algorithm.nulls.DefaultNullComparator;
import com.sahakian.jsondiff.algorithm.object.LeftJoinObjectComparator;
import com.sahakian.jsondiff.algorithm.object.SimpleObjectComparator;
import com.sahakian.jsondiff.algorithm.other.DefaultOtherComparator;
import com.sahakian.jsondiff.algorithm.primitive.DefaultPrimitiveComparator;

public enum AlgorithmEnum {
    DEFAULT(defaultAlgorithmModule()),
    SIMPLE_ARRAY_AND_SIMPLE_OBJECT(simpleAndSimpleAlgorithmModule()),
    SIMPLE_ARRAY_AND_LEFT_JOIN_OBJECT(simpleAndLeftJoinAlgorithmModule()),
    SIMLAR_ARRAY_AND_LEFTJOIN_OBJECT(similarAndLeftJoinAlgorithmModule()),
    MOST_COMMONLY_USED(similarAndSimpleAlgorithmModule());

    final private AlgorithmModule algorithmModule;

    AlgorithmEnum(AlgorithmModule algorithmModule) {
        this.algorithmModule = algorithmModule;
    }


    public AlgorithmModule getAlgorithmModule() {
        return algorithmModule;
    }

    private static AlgorithmModule defaultAlgorithmModule() {
        return new AlgorithmModule(new SimpleObjectComparator(), new SimilarArrayComparator(),
                                   new DefaultPrimitiveComparator(), new DefaultNullComparator(), new DefaultOtherComparator());

    }

    private static AlgorithmModule simpleAndSimpleAlgorithmModule() {
        return new AlgorithmModule(new SimpleObjectComparator(), new SimpleArrayComparator(),
                new DefaultPrimitiveComparator(), new DefaultNullComparator(), new DefaultOtherComparator());
    }


    private static AlgorithmModule simpleAndLeftJoinAlgorithmModule() {
        return new AlgorithmModule(new LeftJoinObjectComparator(), new SimpleArrayComparator(),
                                   new DefaultPrimitiveComparator(), new DefaultNullComparator(), new DefaultOtherComparator());
    }

    private static AlgorithmModule similarAndLeftJoinAlgorithmModule() {
        return new AlgorithmModule(new LeftJoinObjectComparator(), new SimilarArrayComparator(),
                new DefaultPrimitiveComparator(), new DefaultNullComparator(), new DefaultOtherComparator());
    }

    private static AlgorithmModule similarAndSimpleAlgorithmModule() {
        return new AlgorithmModule(new SimpleObjectComparator(), new SimilarArrayComparator(),
                new DefaultPrimitiveComparator(), new DefaultNullComparator(), new DefaultOtherComparator());
    }

}