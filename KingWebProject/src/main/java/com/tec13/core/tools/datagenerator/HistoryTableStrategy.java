package com.tec13.core.tools.datagenerator;

import com.tec13.core.tools.datagenerator.fakefield.BaseFieldFaker;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;

import java.lang.reflect.Field;
import java.util.*;

public class HistoryTableStrategy extends FakeStrategy {
    public static final String CHANGE_FREQUNCY_DAY = "DAY";
    public static final String CHANGE_FREQUNCY_MONTH = "MONTH";
    public static final String CHANGE_FREQUNCY_YEAR = "YEAR";
    protected float changeRate = 0.03f;
    protected String changeFrequncy = CHANGE_FREQUNCY_DAY;


    private String historyDateField="SD_ETL_DATE";
    private DateTime fakeBeginDate;
    private DateTime fakeEndDate;

//    private List<Object> allLastestatas = new ArrayList<>();
    private Map<String,Object> allLastestMap = new HashMap<>();
    private List<Object> allHisDatas = new ArrayList<>();
    private List<String> updateFields = new ArrayList<>();

    protected DataGenerator dataGenerator;
    public HistoryTableStrategy(DataGenerator dataGenerator){
        this.dataGenerator = dataGenerator;
    }

    public static HistoryTableStrategy create(DataGenerator dataGenerator){
        return new HistoryTableStrategy(dataGenerator);
    }

    public HistoryTableStrategy dateRange(DateTime fakeBeginDate, DateTime fakeEndDate){
        this.fakeBeginDate = fakeBeginDate;
        this.fakeEndDate = fakeEndDate;
        return this;
    }


    public void execute(){
        int diffNumInFrequncy = 0;

        switch (changeFrequncy){
            case CHANGE_FREQUNCY_DAY:
                diffNumInFrequncy = Days.daysBetween(fakeEndDate,fakeBeginDate).getDays();break;
            case CHANGE_FREQUNCY_MONTH:
                diffNumInFrequncy = Months.monthsBetween(fakeEndDate,fakeBeginDate).getMonths();break;
            case CHANGE_FREQUNCY_YEAR:
                diffNumInFrequncy = Years.yearsBetween(fakeEndDate,fakeBeginDate).getYears();break;
        }
        DateTime currenDateTime = fakeBeginDate;
        while(currenDateTime.compareTo(fakeEndDate) < 0){
            int genDataNum = (int) (dataGenerator.exportFakeNum * changeRate);
            for (int i = 0; i < genDataNum; i++) {
                Object newFakeObj = null;
                if(currenDateTime.isEqual(fakeBeginDate)){
                    newFakeObj = createNewData(currenDateTime);
                    allLastestMap.put(getFakeObjectKey(newFakeObj),newFakeObj);
                }else{
                    boolean needUpdate = RandomUtils.nextFloat(0,1) < changeRate;
                    if(needUpdate){
                        newFakeObj = createUpdateData(currenDateTime);
                    }else{
                        newFakeObj = createNewData(currenDateTime);
                    }
                    allLastestMap.put(getFakeObjectKey(newFakeObj),newFakeObj);
                }

                allHisDatas.add(newFakeObj);
            }

            //next
            switch (changeFrequncy){
                case CHANGE_FREQUNCY_DAY:
                    currenDateTime = currenDateTime.plusDays(1);break;
                case CHANGE_FREQUNCY_MONTH:
                    currenDateTime = currenDateTime.plusMonths(1);break;
                case CHANGE_FREQUNCY_YEAR:
                    currenDateTime = currenDateTime.plusYears(1);break;
            }
            System.out.println(currenDateTime);
        }
        System.out.println(allHisDatas);
        ExportUtils.exportCsv(dataGenerator.exportFilePath,allHisDatas);
    }

    public void addUpdateFields(String updateField){
        updateFields.add(updateField);
    }

    private String getFakeObjectKey(Object newFakeObj){
        List<String> keyFieldNames = dataGenerator.keyFieldNames;
        String keyUniqueStr= "";
        for(String fn:keyFieldNames){
            Field field = FieldUtils.getField(newFakeObj.getClass(), fn, true);
            try {
                Object o = field.get(newFakeObj);
                keyUniqueStr += String.valueOf(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return keyUniqueStr;
    }

    private Object createNewData(DateTime currenDateTime){
        try {
            Object fakedObj = dataGenerator.fakeClazz.newInstance();

            for (Map.Entry<String, BaseFieldFaker> e : dataGenerator.fieldNames.entrySet()) {
                e.getValue().fake(fakedObj);
            }
            Field etlDate = FieldUtils.getField(dataGenerator.fakeClazz, "etlDate",true);
//            etlDate.setAccessible(true);
            etlDate.set(fakedObj,currenDateTime.toString("YYYY-MM-dd"));
            return fakedObj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    private Object createUpdateData(DateTime currenDateTime){
        Set<String> keySet = allLastestMap.keySet();
        int randomKeyIndex = RandomUtils.nextInt(0, keySet.size());
        String randomKeyStr = "";

        Iterator<String> iterator = keySet.iterator();
        int counter=0;
        while(iterator.hasNext()){
            String next = iterator.next();

            if(counter++ == randomKeyIndex){
                randomKeyStr=next;
            }

        }
        Object oldObj = allLastestMap.get(randomKeyStr);
        Object updateObj = createNewData(currenDateTime);
        try {
            Field[] allFields = FieldUtils.getAllFields(oldObj.getClass());
            for (Field f : allFields) {
                if(!updateFields.contains(f.getName())){
                    f.setAccessible(true);
                    Object oldVal = f.get(oldObj);
                    f.set(updateObj,oldVal);
                }
            }
            return updateObj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
