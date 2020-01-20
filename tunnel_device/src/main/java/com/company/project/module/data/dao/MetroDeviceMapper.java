package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.MetroDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MetroDeviceMapper extends Mapper<MetroDevice> {
    void batchSaveProject(List<Map> subList);

    List<MetroDevice> selectByCon(Map params);

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
    /**
     * Eam设备数据
     *
     * @return
     */
    List findEamDevice(Map params);

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
    List<Map> findcheckItem(Map params);

    /***
     * 获取检查所有年份
     * @param params
     * @return
     */
    List<String> getResYear(Map params);
}