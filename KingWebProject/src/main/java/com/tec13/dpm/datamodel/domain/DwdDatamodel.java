package com.tec13.dpm.datamodel.domain;

public class DwdDatamodel extends BaseDataModel{
    private BaseTableMetadata sourceTableDef;
    private BaseTableMetadata deleteTableDef;
    public DwdDatamodel(){

    }

    public static DwdDatamodel create(BaseTableMetadata sourceTableDef){
        DwdDatamodel dm = new DwdDatamodel();
        dm.setSourceTableDef(sourceTableDef);
//        BaseFieldMetadata dpSourceSysField = new BaseFieldMetadata();
//        dpSourceSysField.setComment("湖表来源系统");
//        dpSourceSysField.setName("dp_source_sys");
//        dpSourceSysField.setType("String");
//        dm.addCommonTecField(dpSourceSysField);
        BaseFieldMetadata dpDlkDateField = new BaseFieldMetadata();
        dpDlkDateField.setComment("湖层数据归属日期");
        dpDlkDateField.setName("dp_dlk_date");
        dpDlkDateField.setType("Date");
        dm.addCommonTecField(dpDlkDateField);

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
        return dm;
    }

    public BaseTableMetadata getSourceTableDef() {
        return sourceTableDef;
    }

    public void setSourceTableDef(BaseTableMetadata sourceTableDef) {
        this.sourceTableDef = sourceTableDef;
    }

    public BaseTableMetadata getDeleteTableDef() {
        return deleteTableDef;
    }

    public void setDeleteTableDef(BaseTableMetadata deleteTableDef) {
        this.deleteTableDef = deleteTableDef;
    }
}
