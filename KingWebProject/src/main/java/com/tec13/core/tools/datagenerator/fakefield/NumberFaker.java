package com.tec13.core.tools.datagenerator.fakefield;

import com.tec13.core.tools.datagenerator.DataGenerator;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public class NumberFaker extends BaseFieldFaker {
    public int minNum = 0;
    public int maxNum = Integer.MAX_VALUE;
    public NumberFaker(DataGenerator generator, String fieldName){
        super(generator,fieldName);
    }
    @Override
    public void fake(Object fakeObj) {
        int fakeNum =   RandomUtils.nextInt(minNum,maxNum);
        setFieldValue(fakeObj,fakeNum);
    }

    public NumberFaker setNumberRange(int minNum, int maxNum){
        this.minNum = minNum;
        this.maxNum = maxNum;
        return this;
    }
}
