package com.tec13.core.tools.datagenerator.fakefield;

import com.github.javafaker.Faker;
import com.tec13.core.tools.datagenerator.DataGenerator;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public abstract class BaseFieldFaker {

    protected DataGenerator generator;
    protected Faker faker;
    protected String fieldName;

    public BaseFieldFaker(DataGenerator generator, String fieldName){
        this.generator = generator;
        this.faker = generator.getFaker();
        this.fieldName = fieldName;
    }

    public  void fake(Object fakeObj){
        //Just stub.Do nothing.
    }
    public void setFieldValue(Object fakeObj,Object fakeValue){
        setFieldValue(fakeObj,fieldName,fakeValue);
    }

    public void setFieldValue(Object fakeObj,String fieldName,Object fakeValue){
        Field field = FieldUtils.getField(fakeObj.getClass(), fieldName,true);
        try {
            field.set(fakeObj,fakeValue);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
