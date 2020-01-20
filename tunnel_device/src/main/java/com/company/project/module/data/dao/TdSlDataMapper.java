package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdSlData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TdSlDataMapper extends Mapper<TdSlData> {

    void saveAll(List<TdSlData> list);

    List<Map> getDuctBySegment(String id);

    List<Map> getWarning();

    List<Map> getDuctDate(Map param);

    List<Map> initSLXData(Map map);

    List<Map> getSlByName(Map map);

    List<Map> getLegend(@Param("IS_DENSITY") String emphasis);

    List<Map> getDiffData(Map map);

    List<Map> initPtdSlXData(Map params);

    List<Map> getPtdSLTime(Map params);

    List<Double> getSlScore(Map param);

    int getZMScore(Map param);

    /**
     * 重点区间收敛预警数据
     *
     * @return
     */
    List<Map> getZdjcSlYjData();

    List<Map> getMapDiffData(Map map);

    /***
     * 获取单环的最新收敛信息
     * @param params
     * @return
     */
    List<Map> selectSLByDuct(Map params);
}