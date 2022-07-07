package com.tec13.core.tools.datagenerator.keygenerator;

import com.tec13.core.tools.datagenerator.DataGenerator;
import com.tec13.core.tools.datagenerator.fakefield.BaseFieldFaker;

import java.util.UUID;

public class KeyUuidGenerator extends BaseFieldFaker {

    public KeyUuidGenerator(DataGenerator generator, String fieldName){
        super(generator,fieldName);
    }

    @Override
    public void fake(Object fakeObj) {
        setFieldValue(fakeObj, UUID.randomUUID().toString());
    }
}
