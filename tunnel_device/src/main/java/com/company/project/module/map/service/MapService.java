package com.company.project.module.map.service;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-13-10:40.
 */
public interface MapService {
    /**
     * 地图安全保护区点数据
     * @param params
     * @return
     */
    List<Map> getAqbhqDataForMap(Map params) throws Exception;
    /**
     * 地图监护监测点数据
     * @param params
     * @return
     */
    List<Map> getJhjcDataForMap(Map params) throws Exception;
}
