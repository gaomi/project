package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdCjData;

import java.util.List;
import java.util.Map;

public interface TdCjDataMapper extends Mapper<TdCjData> {
    void saveAll(List<TdCjData> subList);

    List<Map> selectSegment();

    int updateCj(Map map);

    List<Map> getMeterBySegment(String id);

    List<Map> getPointData(Map map);

    List<Map> getDateByTime(Map map);

    List<Map> getCjDate(Map map);

    List<Map> getQlXdate(Map map);

    List<Map> getQlByTime(Map map);

    List<Map> getCjPtd(Map map);

    List<Map> getQLPtd(Map params);

    List<Map> getCjXDate(Map map);

    List<Map> initPtdCjXData(Map map);

    List<Map> getPtdCjData(Map params);

    Double getCjMark(Map param);

    /**
     * 重点区间沉降预警数据
     *
     * @return
     */
    List getZdjcCjYjData();
}