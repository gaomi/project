package com.company.project.module.app.service;

import com.company.project.core.Service;
import com.company.project.module.app.model.AppVersion;
import com.company.project.module.data.model.TdStation;

import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/20.
 */
public interface AppInfoService extends Service<TdStation> {

    /**
     * 查询基本数据，线，站
     *
     * @param params
     * @return
     */
    Map findBaseInfoForApp(Map params);

    /**
     * 手机端查询字典
     *
     * @param module
     * @return
     */
    Map findDictForApp(String module);

    /**
     * 获取所有设备数据
     * @return
     */
    Map findDeviceInfoForApp();

    Map findStationInfoForApp(Map params);

    /**
     * app版本
     * @param params
     * @return
     */
    AppVersion findVersionForApp(Map params);

    /**
     * 手机更新基础数据
     * @return
     */
    Map getBaseInfoDataForApp();
}
