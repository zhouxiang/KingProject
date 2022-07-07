package com.tec13.core.tools.datagenerator.fakefield;

import com.tec13.core.tools.datagenerator.DataGenerator;

import java.util.Random;

public class StringEnumFaker extends BaseFieldFaker {
    protected String[] enumArr;

    public StringEnumFaker(DataGenerator generator, String fieldName){
        super(generator,fieldName);
    }
    @Override
    public void fake(Object fakeObj) {
        int randomIndex = new Random().nextInt(enumArr.length);
        setFieldValue(fakeObj,enumArr[randomIndex]);
    }

    public String[] getEnumArr() {
        return enumArr;
    }

    public StringEnumFaker setEnumArr(String[] enumArr) {
        this.enumArr = enumArr;
        return this;
    }
}
