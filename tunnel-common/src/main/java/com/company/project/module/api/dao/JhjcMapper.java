package com.company.project.module.api.dao;

import com.company.project.core.Mapper;
import com.company.project.module.api.model.JhjcProject;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface JhjcMapper extends Mapper<JhjcProject> {
    @Update("delete form J302_API_JHJC_PROJECT")
    void deleteAll();

    void batchSaveProject(List<JhjcProject> list);

    JhjcProject getProjectInfoById(String id);

    List<JhjcProject> queryAll(Map param);

    List<JhjcProject> queryAll();

    List<JhjcProject> selectByKeyword(Map param);

    List<JhjcProject> selectLimt(Map param);

    Integer getTotal(Map param);

    List<JhjcProject> findByIds(List<String> list);

    /**
     *
     * @param params
     * @return
     */
    List<Map> findDataForMapByV(Map params);

    /**
     * 获取区间监护项目环号
     *
     * @param params
     * @return
     */
    List<Map> getProjectSegment(Map params);
}