package com.company.project.module.api.enums;

public enum JhjcEnum {
    CJY("[沉][年]"),
    CJN("[沉][本]"),
    CJS("[沉][累]"),
    SLC("[收][差]"),
    SLY("[收][本]"),
    SLS("[收][累]"),
    SL("[收]"),
    CJ("[沉]"),
    ;

    private String code;
    private String name;
    JhjcEnum(String name) {
        this.code = this.name();
        this.name = name;
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
