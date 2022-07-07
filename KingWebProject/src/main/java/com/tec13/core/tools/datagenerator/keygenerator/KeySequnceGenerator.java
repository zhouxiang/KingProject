package com.tec13.core.tools.datagenerator.keygenerator;

import com.tec13.core.tools.datagenerator.DataGenerator;
import com.tec13.core.tools.datagenerator.fakefield.BaseFieldFaker;

public class KeySequnceGenerator extends BaseFieldFaker {
//    protected static  final String SEQUENCE = "SEQUENCE";
//    protected static  final String UUID = "UUID";

    public KeySequnceGenerator(DataGenerator generator, String fieldName){
        super(generator,fieldName);
    }

    @Override
    public void fake(Object fakeObj) {
//        int randomIndex = new Random().nextInt(enumArr.length);
//        setFieldValue(fakeObj,enumArr[randomIndex]);
    }
}
