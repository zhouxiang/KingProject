package com.tec13.core.tools.datagenerator.keygenerator;

import com.tec13.core.tools.datagenerator.DataGenerator;
import com.tec13.core.tools.datagenerator.fakefield.BaseFieldFaker;

public class KeyGeneratorStategy {
    public static  final String SEQUENCE = "SEQUENCE";
    public static  final String UUID = "UUID";

    public static BaseFieldFaker create(DataGenerator generator, String fieldName, String strategy){
        BaseFieldFaker dataFaker = null;
        switch (strategy){
            case SEQUENCE: dataFaker = new KeySequnceGenerator(generator,fieldName);break;
            case UUID:  dataFaker = new KeyUuidGenerator(generator,fieldName);break;
        }
        return dataFaker;
    }
}
