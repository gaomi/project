package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.MetroFault;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/07/19.
 */
public interface MetroFaultService extends Service<MetroFault> {

    Object selectAll(Map params);

    Map getDiseTop(String ctx, String time);

    List<Map> findDiseDuct(String line);

    List<Map> getDiseStatis(String data);

    List<Map> getSegmentStatis(Map params);

    List<Map> getDiseAnalyze(Map request);

    List<Map> newFault(Map params);

    List<Map> selectTest();

    Integer updateTest(Map params);

    List<String> getFaultLoc();

    List<Map> getFaultMark(Map param);


    List<Map> getFaultByStaus(Map params);

    /***
     * 添加审核状态
     * @param siData
     */
    void createNewFault(List<Map> siData);
}
