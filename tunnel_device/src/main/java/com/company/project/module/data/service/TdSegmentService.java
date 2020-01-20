package com.company.project.module.data.service;

import com.company.project.module.data.model.TdSegment;
import com.company.project.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/06.
 */
public interface TdSegmentService extends Service<TdSegment> {
    /**
     * 手机端 根据视图查找基本数据
     *
     * @return
     */
    List findBaseDataByV(Map map);

    /**
     * 查询视图
     */
    List findNoGroupSegmentV(Map<String, String> map);

    /**
     * 条件查询区间
     * @param params
     * @return
     */
    List findAllSegment(Map params);

    List<Map> findSegmentInfoForApp(Map map);

    void updateSegmentMileage(TdSegment tdSegment);


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
