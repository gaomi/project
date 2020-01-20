package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.MetroDeviceInfo;

import java.util.List;
import java.util.Map;

public interface MetroDeviceInfoMapper extends Mapper<MetroDeviceInfo> {
    void batchSaveProject(List<Map> subList);

    List<MetroDeviceInfo> selectByCon(Map params);

    /**
     * 根据视图查询设备数据
     *
     * @return
     */
    List<Map> findDeviceInfoForApp();

    /***
     * 获取隧道设备信息
     * @param params
     * @return
     */
    List<Map> getDeviceInfo(Map params);

    /**
     * 设备条件查询
     *
     * @param params
     * @return
     */
    List<Map> findAllDeviceForWeb(Map params);

    /**
     * 设备字典
     *
     * @return
     */
    List<Map> findDeviceTypeDictForWeb();
}