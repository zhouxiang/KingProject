package com.tec13.core.tools.datagenerator;

import com.github.javafaker.Faker;
import com.tec13.core.tools.datagenerator.fakefield.BaseFieldFaker;
import com.tec13.core.tools.datagenerator.fakefield.NameFaker;
import com.tec13.core.tools.datagenerator.fakefield.NumberFaker;
import com.tec13.core.tools.datagenerator.fakefield.StringEnumFaker;
import com.tec13.core.tools.datagenerator.keygenerator.KeyGeneratorStategy;
import org.joda.time.DateTime;

import java.util.*;

public class DataGenerator {
    protected  Class<?> fakeClazz;
    protected Faker faker;
    protected List<String> keyFieldNames;
    protected Map<String, BaseFieldFaker> fieldNames;
//    protected  Object fakeObj;
    protected String exportFilePath;
    protected Long exportFakeNum;
    protected String exportFileType;
    public static final String EXPORT_TYPE_CSV = "csv";

    public DataGenerator(Class<?> clazz) {
        this.fakeClazz = clazz;
        this.faker = new Faker(new Locale("zh-CN"));
        this.fieldNames = new HashMap<>();
        this.exportFileType = EXPORT_TYPE_CSV;
        this.keyFieldNames = new ArrayList<>();
    }

    public static DataGenerator create(Class<?> clazz){
        DataGenerator dg = new DataGenerator(clazz);
        return dg;
    }

    public DataGenerator build(){
        return this;
    }

    public void executeFake(){
//        for(long i =0;i<exportFakeNum;i++){
//            try {
//                Object fakeObj = fakeClazz.newInstance();
//                for (Map.Entry<String, BaseDataFaker> e : fieldNames.entrySet()) {
//                    e.getValue().fake(fakeObj);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new IllegalStateException(e);
//            }
//        }


    }

    public static void main(String[] args){

        DataGenerator result = DataGenerator.create(TestCustomer.class)
                .genPrimaryKey("customerId", KeyGeneratorStategy.UUID)
                .genPersonName("name")
                .genStringEnum("sex",new String[]{"male","female"})
                .genNumber("age",0,120)
                .genNumber("money",0,100000)
                .genAddres("address")
                .fakeStrategy(FakeStrategy.HISTORY_TABLE)
                .exportFilePath("D:\\develop\\test\\fakdata\\test.data")
                .exportFakeNum(20000l)
                .exportFileType(DataGenerator.EXPORT_TYPE_CSV).build();
        System.out.println(result);
        HistoryTableStrategy historyTableStrategy = HistoryTableStrategy.create(result).dateRange(DateTime.now().minusDays(2000), DateTime.now());
        historyTableStrategy.addUpdateFields("money");
        historyTableStrategy.addUpdateFields("etlDate");
        historyTableStrategy.execute();
    }

    private DataGenerator genAddres(String address) {
        return this;
    }

    private DataGenerator fakeStrategy(String strategy) {
        FakeStrategy.create(this,strategy);
        return this;
    }

    private DataGenerator genPrimaryKey(String fieldName, String keyGeneratorStategy) {
        keyFieldNames.add(fieldName);
        fieldNames.put(fieldName,KeyGeneratorStategy.create(this,fieldName,keyGeneratorStategy));
        return this;
    }

    private DataGenerator exportFileType(String exportFileType) {
        this.exportFileType = exportFileType;
        return this;
    }

    public DataGenerator genPersonName(String fieldName) {
        fieldNames.put(fieldName,new NameFaker(this,fieldName));
        return this;
    }

    public DataGenerator genNumber(String fieldName) {
        fieldNames.put(fieldName,new NumberFaker(this,fieldName));
        return this;
    }

    public DataGenerator genNumber(String fieldName, int minNum, int maxNum) {
        NumberFaker numberFaker = new NumberFaker(this,fieldName).setNumberRange(minNum,maxNum);
        fieldNames.put(fieldName,numberFaker);
        return this;
    }

    public DataGenerator genStringEnum(String fieldName,String[] enumArr) {
        StringEnumFaker f = new StringEnumFaker(this,fieldName).setEnumArr(enumArr);
        fieldNames.put(fieldName,f);
        return this;
    }

    public DataGenerator exportFilePath(String exportFilePath) {
        this.exportFilePath = exportFilePath;
        return this;
    }

    public DataGenerator exportFakeNum(Long exportFakeNum) {
        this.exportFakeNum = exportFakeNum;
        return this;
    }

    public Faker getFaker() {
        return faker;
    }
}
