package com.tec13.core.tools.datagenerator;

import com.tec13.core.tools.datagenerator.fakefield.BaseFieldFaker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FakeObjWrapper {
    protected Object originObj;

    protected List<FakeFieldWrapper> addFields;
    protected DataGenerator dataGenerator;

    public FakeObjWrapper(Object originObj){
        this.originObj = originObj;
        addFields = new ArrayList<>();
    }

    public void addFakeField(FakeFieldWrapper f){
        addFields.add(f);
    }

    public FakeObjWrapper createNewObj(Object originObj){
        try {
            Object fakedObj = originObj.getClass().newInstance();

            for (Map.Entry<String, BaseFieldFaker> e : dataGenerator.fieldNames.entrySet()) {
                e.getValue().fake(fakedObj);
            }
            FakeObjWrapper w = new FakeObjWrapper(fakedObj);
            for (FakeFieldWrapper addField : w.addFields) {
//                addField.getFieldFaker().fake();
            }

            return w;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
