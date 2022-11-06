package com.jdiff;

import com.jdiff.algorithm.AlgorithmModule;
import com.jdiff.algorithm.array.SimilarArrayComparator;
import com.jdiff.algorithm.array.SimpleArrayComparator;
import com.jdiff.algorithm.nulls.DefaultNullComparator;
import com.jdiff.algorithm.object.LeftJoinObjectComparator;
import com.jdiff.algorithm.object.SimpleObjectComparator;
import com.jdiff.algorithm.other.DefaultOtherComparator;
import com.jdiff.algorithm.primitive.DefaultPrimitiveComparator;

public enum AlgorithmEnum {
    DEFAULT(defaultAlgorithmModule()),
    SIMPLE_ARRAY_AND_SIMPLE_OBJECT(simpleAndSimpleAlgorithmModule()),
    SIMPLE_ARRAY_AND_LEFT_JOIN_OBJECT(simpleAndLeftJoinAlgorithmModule()),
    SIMLAR_ARRAY_AND_LEFT_JOIN_OBJECT(similarAndLeftJoinAlgorithmModule()),
    MOST_COMMONLY_USED(similarAndSimpleAlgorithmModule());

    final private AlgorithmModule algorithmModule;

    AlgorithmEnum(AlgorithmModule algorithmModule) {
        this.algorithmModule = algorithmModule;
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

    public AlgorithmModule getAlgorithmModule() {
        return algorithmModule;
    }

}