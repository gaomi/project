package com.company.project.module.home.util.model;

public enum MergeEnum {

    HORIZONAL("行合并"),
    VERTICALLY("列合并");

    private String code;
    private String name;

    MergeEnum(String name){
        this.code = this.name();
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
