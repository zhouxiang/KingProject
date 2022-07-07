package com.tec13.dpm.datamodel.domain;

import java.util.Date;

public class DlkDatamodel extends BaseDataModel{
    private BaseTableMetadata sourceTableDef;
    public DlkDatamodel(){

    }

    public static DlkDatamodel create(BaseTableMetadata sourceTableDef){
        DlkDatamodel dm = new DlkDatamodel();
        dm.setSourceTableDef(sourceTableDef);
        BaseFieldMetadata dpSourceSysField = new BaseFieldMetadata();
        dpSourceSysField.setComment("湖表来源系统");
        dpSourceSysField.setName("dp_source_sys");
        dpSourceSysField.setType("String");
        dm.addCommonTecField(dpSourceSysField);

        BaseFieldMetadata dpEtlTimeField = new BaseFieldMetadata();
        dpEtlTimeField.setComment("ETL操作数据");
        dpEtlTimeField.setName("dp_etltime");
        dpEtlTimeField.setType("Date");
        dm.addCommonTecField(dpEtlTimeField);

        BaseFieldMetadata dpDateField = new BaseFieldMetadata();
        dpDateField.setComment("数据归属日期");
        dpDateField.setName("dp_date");
        dpDateField.setType("Date");
        dm.addCommonTecField(dpDateField);

        dm.setNameSuffix("_HIS");
        return dm;
    }

    public BaseTableMetadata getSourceTableDef() {
        return sourceTableDef;
    }

    public void setSourceTableDef(BaseTableMetadata sourceTableDef) {
        this.sourceTableDef = sourceTableDef;
    }
}
