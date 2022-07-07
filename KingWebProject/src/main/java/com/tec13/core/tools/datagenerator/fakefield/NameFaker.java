package com.tec13.core.tools.datagenerator.fakefield;

import com.tec13.core.tools.datagenerator.DataGenerator;

public class NameFaker extends BaseFieldFaker {
    public NameFaker(DataGenerator generator, String fieldName){
        super(generator,fieldName);
    }
    @Override
    public void fake(Object fakeObj) {
        setFieldValue(fakeObj,faker.name().fullName());
    }
}
