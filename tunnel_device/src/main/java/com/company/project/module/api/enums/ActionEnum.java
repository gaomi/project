package com.company.project.module.api.enums;

/**
 * 操作码，判断增删改查
 */
public enum ActionEnum {

    //修改操作
    UPDATE("修改"),

    //添加操作
    INSERT("添加"),

    //删除操作
    DELETE("删除");
    private String code;
    private String name;
    ActionEnum(String name) {
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
