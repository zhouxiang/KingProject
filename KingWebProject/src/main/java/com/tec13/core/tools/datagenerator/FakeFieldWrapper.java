package com.tec13.core.tools.datagenerator;

import com.tec13.core.tools.datagenerator.fakefield.BaseFieldFaker;

public class FakeFieldWrapper {
    protected Object value;
    protected String name;
    protected BaseFieldFaker fieldFaker;

    public FakeFieldWrapper( String name,Object value,BaseFieldFaker fieldFaker) {
        this.value = value;
        this.name = name;
        this.fieldFaker = fieldFaker;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseFieldFaker getFieldFaker() {
        return fieldFaker;
    }

    public void setFieldFaker(BaseFieldFaker fieldFaker) {
        this.fieldFaker = fieldFaker;
    }
}
