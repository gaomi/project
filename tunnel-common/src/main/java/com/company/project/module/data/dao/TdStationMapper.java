package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdStation;

import java.util.List;
import java.util.Map;

public interface TdStationMapper extends Mapper<TdStation> {
    /**
     * 手机端查询线路基本信息
     *
     * @return
     */
    List findBaseInfoForApp(Map map);

    /**
     * 手机端字典数据
     *
     * @return
     */
    List findBaseDictForApp(Map map);

    List<Map> selectStation(String id);
}