package com.company.project.module.inspect.dao;

import com.company.project.core.Mapper;
import com.company.project.module.inspect.model.DeviceFaultType;

import java.util.List;
import java.util.Map;

public interface DeviceFaultTypeMapper extends Mapper<DeviceFaultType> {
    /**
     * 根据视图查询
     *
     * @param params
     * @return
     */
    List<Map> findDeviceFaultTypeByV(Map params);
}