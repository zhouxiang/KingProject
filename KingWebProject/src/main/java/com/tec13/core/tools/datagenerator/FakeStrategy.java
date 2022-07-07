package com.tec13.core.tools.datagenerator;

public class FakeStrategy {
    public static final String HISTORY_TABLE = "HISTORY_TABLE";

    public static FakeStrategy create(DataGenerator generator, String strategy){
        FakeStrategy fakeStrategy = null;
        switch (strategy){
            case HISTORY_TABLE: fakeStrategy = new HistoryTableStrategy(generator);break;
        }
        return fakeStrategy;
    }

    public void execute(){

    }
}
