package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdMaintainInfo;

import java.util.List;
import java.util.Map;

public interface TdMaintainInfoMapper extends Mapper<TdMaintainInfo> {
    void insertSeg(Map map);

    List<TdMaintainInfo> selectByCon(Map params);

    List<Map> findSegment();

    List<Map> getDevice(Map map);

    void saveDeviceMainTain(Map result);

    List<TdMaintainInfo> selectByDevice(Map params);

    void updateFault(Map map);
}