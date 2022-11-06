package com.jdiff;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jdiff.model.DiffContext;
import com.jdiff.model.PathModule;
import com.jdiff.model.Result;
import com.jdiff.model.ResultConvertUtil;
import com.jdiff.algorithm.AlgorithmModule;
import com.jdiff.algorithm.array.ArrayComparator;
import com.jdiff.algorithm.array.SimilarArrayComparator;
import com.jdiff.algorithm.nulls.DefaultNullComparator;
import com.jdiff.algorithm.nulls.NullComparator;
import com.jdiff.algorithm.object.ObjectComparator;
import com.jdiff.algorithm.object.SimpleObjectComparator;
import com.jdiff.algorithm.other.DefaultOtherComparator;
import com.jdiff.algorithm.other.OtherComparator;
import com.jdiff.algorithm.primitive.DefaultPrimitiveComparator;
import com.jdiff.algorithm.primitive.PrimitiveComparator;

import java.util.List;

public class Diff {
    private AlgorithmEnum algorithmEnum;
    private List<String> specialPath;
    private List<String> noisePahList;
    private ObjectComparator objectComparator;
    private ArrayComparator arrayComparator;
    private PrimitiveComparator primitiveComparator;
    private NullComparator nullComparator;
    private OtherComparator otherComparator;

    public Diff() {
    }

    public List<Result> diffElement(JsonElement a, JsonElement b) {
        DiffContext diffContext;
        PathModule pathModule = new PathModule(noisePahList, specialPath);
        if (algorithmEnum != null) {
            diffContext = algorithmEnum.getAlgorithmModule().diffElement(a, b, pathModule);
        } else if (objectComparator == null && arrayComparator == null && primitiveComparator == null && nullComparator == null && otherComparator == null) {
            diffContext = AlgorithmEnum.DEFAULT.getAlgorithmModule().diffElement(a, b, pathModule);
        } else {
            constructDefaultComparator();
            diffContext = new AlgorithmModule(objectComparator, arrayComparator, primitiveComparator, nullComparator, otherComparator).diffElement(a, b, pathModule);
        }
        return ResultConvertUtil.constructResult(diffContext);
    }

    public List<Result> diff(String strA, String strB) {
        return diffElement(JsonParser.parseString(strA), JsonParser.parseString(strB));
    }

    private void constructDefaultComparator() {
        if (objectComparator == null) {
            objectComparator = defaultObjectComparator();
        }
        if (arrayComparator == null) {
            arrayComparator = defaultArrayComparator();
        }
        if (primitiveComparator == null) {
            primitiveComparator = defaultPrimitiveComparator();
        }
        if (nullComparator == null) {
            nullComparator = defaultNullComparator();
        }
        if (otherComparator == null) {
            otherComparator = defaultOtherComparator();
        }
    }

    private ObjectComparator defaultObjectComparator() {
        return new SimpleObjectComparator();
    }

    private ArrayComparator defaultArrayComparator() {
        return new SimilarArrayComparator();
    }

    private PrimitiveComparator defaultPrimitiveComparator() {
        return new DefaultPrimitiveComparator();
    }

    private NullComparator defaultNullComparator() {
        return new DefaultNullComparator();
    }

    private OtherComparator defaultOtherComparator() {
        return new DefaultOtherComparator();
    }

    public Diff withAlgorithmEnum(AlgorithmEnum algorithmEnum) {
        this.algorithmEnum = algorithmEnum;
        return this;
    }

    public Diff withSpecialPath(List<String> specialPath) {
        this.specialPath = specialPath;
        return this;
    }

    public Diff withNoisePahList(List<String> noisePahList) {
        this.noisePahList = noisePahList;
        return this;
    }

    public Diff withObjectComparator(ObjectComparator objectComparator) {
        this.objectComparator = objectComparator;
        return this;
    }

    public Diff withArrayComparator(ArrayComparator arrayComparator) {
        this.arrayComparator = arrayComparator;
        return this;
    }

    public Diff withPrimitiveAlgorithm(PrimitiveComparator primitiveComparator) {
        this.primitiveComparator = primitiveComparator;
        return this;
    }

    public Diff withNullComparator(NullComparator nullComparator) {
        this.nullComparator = nullComparator;
        return this;
    }

    public Diff withOtheComparator(OtherComparator otherComparator) {
        this.otherComparator = otherComparator;
        return this;
    }
}
