package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.DeviceLevel;

import java.util.List;
import java.util.Map;

public interface DeviceLevelMapper extends Mapper<DeviceLevel> {
    /**
     * 查询设备分类
     *
     * @return
     */
    List<Map> selectAllDeviceLevel();
}