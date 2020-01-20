package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.MetroDevice;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/22.
 */
public interface MetroDeviceService extends Service<MetroDevice> {

    void batchSave(List<Map> deviceInfos);

    List<MetroDevice> selectByCon(Map params);

    /***
     * 获取隧道设备信息
     * @param params
     * @return
     */
    List<Map> getDeviceInfo(Map params);

    /**
     * 根据条件查找设备
     * @param params
     * @return
     */
    List findAllDevice(Map params);

    /**
     * 设备字典
     * @return
     */
    Map findDeviceTypeDict();
}
