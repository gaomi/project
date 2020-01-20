package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.TdCjData;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/07/30.
 */
public interface TdCjDataService extends Service<TdCjData> {


    void saveAll(List<TdCjData> list);

    List<Map> selectSegment();

    int updateCj(Map map);

    List<Map> getDateByTime(Map map);

    List<Map> getCjDate(Map map);

    List<Map> getPointData(Map map);

    List<Map> getQlXdate(Map map);

    List<Map> getQlByTime(Map map);

    List<Map> getCjPtd(Map map);

    List<Map> getQLPtd(Map params);

    List<Map> getCjXDate(Map map);

    List<Map> getPtdCjXDate(Map params);

    List<Map> getPtdCjData(Map params);

    List<Map> getMapDateByTime(Map map);

    Double getCjMark(Map param);
}
