package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.MetroFault;

import java.util.List;
import java.util.Map;

public interface MetroFaultMapper extends Mapper<MetroFault> {

    List<Map> getDiseTop(Map params);

    List<Map> findDiseDuct(String line);


    List<Map> getDiseStatis(String data);

    /**
     * 报表模块
     *
     * @param params
     * @return
     */

    List<Map> newFault(Map params);

    /**
     * 报表模块
     *
     * @param params
     * @return
     */
    List<Map> getDiseAnalyze(Map params);

    List<Map> selectByParam(Map params);

    List<Map> getSegmentStatis(Map params);

    List<Map> selectTest();

    Integer updateTest(Map params);

    List<String> getFaultLoc();

    List<Map> getFaultMark(Map param);

    List<Map> getFaultByStaus(Map params);

    List<Map> getProjectSegment(Map params);
}