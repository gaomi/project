package com.company.project.module.inspect.service;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    /**
     * 设备故障分类
     *
     * @param params
     * @return
     */
    List findAllDeviceFaultType(Map params);

    /**
     * 设备故障分类字典
     *
     * @return
     */
    Map<String, Object> findDeviceFaultTypeDict();

    /**
     * 设备分类
     *
     * @param params
     * @return
     */
    List findAllDeviceLevel(Map params);

    /**
     * 设备数据
     *
     * @param params
     * @return
     */
    List findAllDevice(Map params);

    /***
     * 获取设备检查项纪录
     * @param params
     * @return
     */
    List findCheckDevice(Map params);

    /***
     * 根据设备获取设备检查项数据
     * @param params
     * @return
     */
    Map findcheckItem(Map params);

    /***
     * 获取检查项
     * @return
     */
    Map<String, Object> getDict(Map params);
}
