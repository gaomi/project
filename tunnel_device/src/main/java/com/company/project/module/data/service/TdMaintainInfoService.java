package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.TdMaintainInfo;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/27.
 */
public interface TdMaintainInfoService extends Service<TdMaintainInfo> {

    void insert(Map map);

    List<TdMaintainInfo> selectByCon(Map params);

    List<Map> findSegment();

    List<Map> getDevice(Map map);

    void saveDeviceMainTain(Map result);

    List<TdMaintainInfo> selectByDevice(Map params);

    void updateFault(Map map);
}
