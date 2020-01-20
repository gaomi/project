package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.TdSlData;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/03.
 */
public interface TdSlDataService extends Service<TdSlData> {


    void saveAll(List<TdSlData> list_mer);

    List<Map> initSLXData(Map map);

    List<Map> getSlByName(Map map);

    List<Map> getWarning();

    List<Map> getLegend(String emphasis);

    List<Map> getDiffData(Map map);

    List<Map> getDuctDate(Map param);

    List<Map> initPtdSlXData(Map params);

    List<Map> getPtdSLTime(Map params);

    List<Map> getMapSlByName(Map map);

    List<Map> getMapDiffData(Map map);

    List<Double> getSlScore(Map param);

    int getZMScore(Map param);
}
