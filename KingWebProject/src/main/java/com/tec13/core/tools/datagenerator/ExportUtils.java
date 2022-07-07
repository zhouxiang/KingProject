package com.tec13.core.tools.datagenerator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExportUtils {

    public static void exportCsv(String exportPath, List<Object> fakeObjList){
        if(fakeObjList.isEmpty()){
            throw new IllegalStateException("Empty Data List!!");
        }
        Object firstObj = fakeObjList.get(0);
        Field[] allFields = FieldUtils.getAllFields(firstObj.getClass());

        List<String> exportStringList = new ArrayList<>();
        for (int i = 0; i < fakeObjList.size(); i++) {
            Object o = fakeObjList.get(i);
            List<Object> ovalueList = new ArrayList<>();
            for (int j = 0; j < allFields.length; j++) {
                Field f = allFields[j];

                try {
                    f.setAccessible(true);
                    Object oVal = f.get(o);
                    ovalueList.add(oVal);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("IllegalAccessException~~",e);
                }
            }

            exportStringList.add(StringUtils.join(ovalueList,","));

        }
        try {
            FileUtils.writeLines(new File(exportPath),exportStringList);
        } catch (IOException e) {
            throw new RuntimeException("write faild~~",e);
        }
    }
}
