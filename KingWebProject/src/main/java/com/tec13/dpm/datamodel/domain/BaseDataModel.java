package com.tec13.dpm.datamodel.domain;

import java.util.ArrayList;
import java.util.List;

public class BaseDataModel {
    private List<BaseFieldMetadata> commonTecFields;
    private String nameSuffix;
    private String namePrefix;

    public BaseDataModel(){
        commonTecFields = new ArrayList<>();
    }

    public void addCommonTecField(BaseFieldMetadata tecField){
        commonTecFields.add(tecField);
    }

    public List<BaseFieldMetadata> getCommonTecFields() {
        return commonTecFields;
    }

    public void setCommonTecFields(List<BaseFieldMetadata> commonTecFields) {
        this.commonTecFields = commonTecFields;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }
}
