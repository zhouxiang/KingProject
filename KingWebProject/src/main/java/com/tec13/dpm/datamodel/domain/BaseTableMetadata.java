package com.tec13.dpm.datamodel.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseTableMetadata {
    private String name;
    private String type;
    private String owner;
    private String seprator;
    private String comment;
    private String storeFormat;
    private List<BaseFieldMetadata> primaryKeys;
    private List<BaseFieldMetadata> bucketKeys;
    private List<BaseFieldMetadata> partionKeys;
    //排序字段
    private List<BaseFieldMetadata> sortKeys;
    //增量时间戳字段 或 更新时间戳字段
    private List<BaseFieldMetadata> incrDateKeys;

    private BaseFieldMetadata deleteDateKey;

    private Date createTime;
    private String databaseName;

    private List<BaseFieldMetadata> fields;

    public BaseTableMetadata(){
        primaryKeys = new ArrayList<>();
        bucketKeys = new ArrayList<>();
        partionKeys = new ArrayList<>();
        fields = new ArrayList<>();
        sortKeys = new ArrayList<>();
        incrDateKeys = new ArrayList<>();
    }

    public void addPrimaryKeys(BaseFieldMetadata primaryKey){
        this.primaryKeys.add(primaryKey);
    }
    public void addSortKeys(BaseFieldMetadata partionKey){
        this.sortKeys.add(partionKey);
    }
    public void addIncrDateKeys(BaseFieldMetadata partionKey){
        this.incrDateKeys.add(partionKey);
    }
    public void addPartionKey(BaseFieldMetadata partionKey){
        this.partionKeys.add(partionKey);
    }

    public void addField(BaseFieldMetadata f){
        fields.add(f);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSeprator() {
        return seprator;
    }

    public void setSeprator(String seprator) {
        this.seprator = seprator;
    }

    public String getStoreFormat() {
        return storeFormat;
    }

    public void setStoreFormat(String storeFormat) {
        this.storeFormat = storeFormat;
    }

    public List<BaseFieldMetadata> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<BaseFieldMetadata> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public List<BaseFieldMetadata> getBucketKeys() {
        return bucketKeys;
    }

    public void setBucketKeys(List<BaseFieldMetadata> bucketKeys) {
        this.bucketKeys = bucketKeys;
    }

    public List<BaseFieldMetadata> getPartionKeys() {
        return partionKeys;
    }

    public void setPartionKeys(List<BaseFieldMetadata> partionKeys) {
        this.partionKeys = partionKeys;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<BaseFieldMetadata> getFields() {
        return fields;
    }

    public void setFields(List<BaseFieldMetadata> fields) {
        this.fields = fields;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<BaseFieldMetadata> getSortKeys() {
        return sortKeys;
    }

    public void setSortKeys(List<BaseFieldMetadata> sortKeys) {
        this.sortKeys = sortKeys;
    }

    public List<BaseFieldMetadata> getIncrDateKeys() {
        return incrDateKeys;
    }

    public void setIncrDateKeys(List<BaseFieldMetadata> incrDateKeys) {
        this.incrDateKeys = incrDateKeys;
    }

    public BaseFieldMetadata getDeleteDateKey() {
        return deleteDateKey;
    }

    public void setDeleteDateKey(BaseFieldMetadata deleteDateKey) {
        this.deleteDateKey = deleteDateKey;
    }
}
