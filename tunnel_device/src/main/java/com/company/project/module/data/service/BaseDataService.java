package com.company.project.module.data.service;

import java.util.List;
import java.util.Map;

public interface BaseDataService {

    Map findBaseInfoForApp(Map params);


    List<Map> findSegmentInfoForApp(Map params);

    /**
     * 获取站的基本数据
     * @param params
     * @return
     */
    Map findStationInfoForWeb(Map params);

    /**
     * 获取数据处理页面字典
     * @param params
     * @return
     */
    Map findDataDictForWeb(Map params);
}
