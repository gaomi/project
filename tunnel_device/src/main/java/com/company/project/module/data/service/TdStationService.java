package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.TdStation;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/06.
 */
public interface TdStationService extends Service<TdStation> {

    /**
     * 条件查找站数据
     *
     * @param params
     * @return
     */
    List findAllStations(Map params);
}
