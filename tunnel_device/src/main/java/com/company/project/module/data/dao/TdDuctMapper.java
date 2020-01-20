package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdDuct;

import java.util.List;
import java.util.Map;

public interface TdDuctMapper extends Mapper<TdDuct> {
    List<TdDuct> findByMap(Map map);

    Integer updateSeg(Map map);

    List<Map> getAllDuct(Map map);

    List<Map> selectSegmentDuct();

    void batchCreateSeq(List<Map> list);

    List<Map> selectRange(Map params);
    List<Map> selectRange2(Map params);

    List<Map> getPangTongDao(Map params);
    /***
     *获取区间环号
     * @param param
     * @return
     */
    List<Map> getSegmentDuct(Map param);

    /***
     * 更新环号的里程
     * @param param
     */
    void updateMileage(Map param);

    /***
     * 获取环号
     * @param params
     * @return
     */
    List<Map> selectDuct(Map params);
}