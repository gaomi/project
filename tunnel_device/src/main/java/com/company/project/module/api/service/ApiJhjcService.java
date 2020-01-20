package com.company.project.module.api.service;

import com.company.project.core.Result;
import com.company.project.core.Service;
import com.company.project.module.api.model.JhjcProject;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/06/25.
 */
public interface ApiJhjcService extends Service<JhjcProject> {


    /**
     * 获取监护监测项目信息
     */
    void getProjectInfo();

    /**
     * 获取监护监测项目布点图
     */
    List<Map> getProjectLayers(String id);

    void updateByJhjc(JhjcProject jhjcProject);

    JhjcProject getProjectInfoById(String id);

    Map<String, Object> selectLimt(Integer num, Integer size, Map param);

    List<Map> getProjecStatus(String id);

    List getDetailById(String id);

    List<JhjcProject> findByIds(List<String> list);

    JhjcProject selectById(String s);

    List<JhjcProject> selectAll(Map param);

    List<JhjcProject> queryAll();

    List<Map> getProjectState(String id);

    List<Map> getProjectPoint(Map map);

    /***
     * 获取监护监测数据字典
     * @return
     */
    Map getDictCheck();
}
