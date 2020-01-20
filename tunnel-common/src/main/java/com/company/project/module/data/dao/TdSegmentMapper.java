package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdSegment;

import java.util.List;
import java.util.Map;

public interface TdSegmentMapper extends Mapper<TdSegment> {

    List findNoGroupSegment(Map<String, String> map);

    /**
     * 不包含风井的视图
     *
     * @param map
     * @return
     */
    List findNoGroupSegmentV(Map<String, String> map);

    /**
     * 手机端查询视图
     *
     * @param map
     * @return
     */
    List findBaseDataByV(Map<String, String> map);


    List<Map> findSegmentByLine2(Map<String, String> map);


    List<Map> findSegmentInfoForApp(Map param);

    /**
     * 查询所有区间的NO_UD_UUID
     *
     * @return
     */
    List<Map> findNoUDIdByV();

    /**
     * 查询所有区间
     *
     * @param map
     * @return
     */
    List findAllSegmentByV(Map map);

    List findSegmentByLine(Map<String, String> map);


    List<Map> getScopeDuct(Map param);

    /***
     * 更新区间里程标
     * @param tdSegment
     */
    void updateSegmentFleage(TdSegment tdSegment);

    /***
     * 获取风井区间
     * @param params 不分风井区间
     * @return
     */
    List<Map> getGroupSegment(Map params);
    /***
     * 根据分风井获取不分风井的区间ID
     * @param params
     * @return
     */
    List<Map> getSegmentByGroup(Map params);
    /***
     * 更新区间环号里程
     * @param param
     */
    void updateSegment(List<Map> param);
}