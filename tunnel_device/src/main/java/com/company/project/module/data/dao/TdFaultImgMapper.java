package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdFaultImg;

import java.util.List;
import java.util.Map;

public interface TdFaultImgMapper extends Mapper<TdFaultImg> {
    List<Map> getImageById(Map ids);


    void saveAll(List<TdFaultImg> subList);
}