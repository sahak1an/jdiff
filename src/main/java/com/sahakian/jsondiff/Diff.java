package com.sahakian.jsondiff;

import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sahakian.jsondiff.algorithm.AlgorithmModule;
import com.sahakian.jsondiff.algorithm.array.ArrayComparator;
import com.sahakian.jsondiff.algorithm.array.SimilarArrayComparator;
import com.sahakian.jsondiff.algorithm.nulls.DefaultNullComparator;
import com.sahakian.jsondiff.algorithm.nulls.NullComparator;
import com.sahakian.jsondiff.algorithm.object.ObjectComparator;
import com.sahakian.jsondiff.algorithm.object.SimpleObjectComparator;
import com.sahakian.jsondiff.algorithm.other.DefaultOtherComparator;
import com.sahakian.jsondiff.algorithm.other.OtherComparator;
import com.sahakian.jsondiff.algorithm.primitive.DefaultPrimitiveComparator;
import com.sahakian.jsondiff.algorithm.primitive.PrimitiveComparator;
import com.sahakian.jsondiff.model.DiffContext;
import com.sahakian.jsondiff.model.PathModule;
import com.sahakian.jsondiff.model.Result;
import com.sahakian.jsondiff.model.ResultConvertUtil;

public class Diff {
    private AlgorithmEnum algorithmEnum;
    private List<String> specialPath;
    private List<String> noisePahList;
    private ObjectComparator objectComparator;
    private ArrayComparator arrayComparator;
    private PrimitiveComparator primitiveComparator;
    private NullComparator nullComparator;
    private OtherComparator otherComparator;

    public List<Result> diffElement(JsonElement a, JsonElement b) {
        DiffContext diffContext;
        PathModule pathModule = new PathModule(noisePahList, specialPath);
        if(algorithmEnum != null){
            diffContext =  algorithmEnum.getAlgorithmModule().diffElement(a, b, pathModule);
        }
        else if(objectComparator == null && arrayComparator == null && primitiveComparator == null && nullComparator == null && otherComparator == null){
            diffContext = AlgorithmEnum.DEFAULT.getAlgorithmModule().diffElement(a, b, pathModule);
        }
        else {
            constrcutDefaultComparator();
            diffContext = new AlgorithmModule(objectComparator, arrayComparator, primitiveComparator, nullComparator, otherComparator).diffElement(a, b, pathModule);
        }
        return ResultConvertUtil.constructResult(diffContext);
    }

    public List<Result> diff(String strA, String strB) {
        return diffElement(JsonParser.parseString(strA), JsonParser.parseString(strB));
    }

    public Diff() {
    }

    private void constrcutDefaultComparator() {
        if(objectComparator == null){
            objectComparator = defaultObjectComparator();
        }
        if(arrayComparator == null){
            arrayComparator = defaultArrayComparator();
        }
        if(primitiveComparator == null){
            primitiveComparator = defaultPrimitiveComparator();
        }
        if(nullComparator == null){
            nullComparator = defaultNullComparator();
        }
        if(otherComparator == null){
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

    public Diff withAlgorithmEnum(AlgorithmEnum algorithmEnum){
        this.algorithmEnum = algorithmEnum;
        return this;
    }

    public Diff withSpecialPath(List<String> specialPath){
        this.specialPath = specialPath;
        return this;
    }

    public Diff withNoisePahList(List<String> noisePahList){
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
