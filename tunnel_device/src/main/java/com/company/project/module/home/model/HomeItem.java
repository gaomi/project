package com.company.project.module.home.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HomeItem {
    //序號
    private String id;
    //线路
    private String line;
    //路径
    private String url;
    //管片
    private String temp;
    //描述
    private String desc;
    //仪表盘最大值
    private String max;
    //值
    private Object value;
    //颜色
    private String color;
    //累计病害
    private String diseSum;
    //本次病害
    private String diseThis;
    //累计维修
    private String repSum;
    //本次维修
    private String repThis;

    public HomeItem(String line, String temp, String desc, String url) {
        this.line = line;
        this.temp = temp;
        this.desc = desc;
        this.url = url;
    }

    public HomeItem(String line, String max, String value) {
        this.line = line;
        this.max = max;
        this.value = value;
    }

    public HomeItem(String line, String diseSum, String diseThis, String repSum, String repThis) {
        this.line = line;
        this.diseSum = diseSum;
        this.diseThis = diseThis;
        this.repSum = repSum;
        this.repThis = repThis;
    }

    public HomeItem(String line, String max) {
        this.line = line;
        this.max = max;
    }

    public HomeItem(String line, Object[] value, String color) {
        this.line = line;
        this.value = value;
        this.color = color;
    }

}
