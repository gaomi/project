package com.company.project.module.home.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SectionItem {
    //数据名称
    private String name;
    //权重
    private String weight;
    //颜色列表
    private Object colorList;
    //数据
    private Object data;
    //yAxisIndex
    private Integer yAxisIndex;
    //统计数
    private Integer count;

    public SectionItem(String name, String weight, Object colorList, Object data) {
        this.name = name;
        this.weight = weight;
        this.colorList = colorList;
        this.data = data;
    }

    public SectionItem(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    public SectionItem(String name, Integer yAxisIndex, Object data) {
        this.name = name;
        this.yAxisIndex = yAxisIndex;
        this.data = data;
    }


}
