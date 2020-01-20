package com.company.project.module.segment.enums;

public enum ProjectEnum {
    TCXX("图层信息"),
    WGSG("违规施工"),
    BHXX("病害信息"),
    BJXM("报警项目"),
    JHXM("监护项目"),
    WXXX("维修信息");

    private String code;
    private String name;
    ProjectEnum(String name) {
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
