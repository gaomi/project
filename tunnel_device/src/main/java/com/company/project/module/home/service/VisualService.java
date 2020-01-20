package com.company.project.module.home.service;

import java.util.List;
import java.util.Map;

public interface VisualService {
    Map getDiseDesc(Map params);

    /**
     * 获取健康度
     *
     * @return
     */
    List<Map> getHealthMarck();

    /**
     * 获取重点监测预警数据
     *
     * @return
     */
    Map getZdjcYjData();
}
