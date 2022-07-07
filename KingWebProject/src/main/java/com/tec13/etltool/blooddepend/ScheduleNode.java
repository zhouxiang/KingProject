package com.tec13.etltool.blooddepend;

import com.tec13.core.server.domain.NodeDomain;

public class ScheduleNode extends NodeDomain {
    private String name;
    private String cronExp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCronExp() {
        return cronExp;
    }

    public void setCronExp(String cronExp) {
        this.cronExp = cronExp;
    }

    @Override
    public String toString() {
        return "ScheduleNode{" +
                "name='" + name + '\'' +
                ", cronExp='" + cronExp + '\'' +
                '}';
    }
}
