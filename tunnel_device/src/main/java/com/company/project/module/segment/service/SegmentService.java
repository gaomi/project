package com.company.project.module.segment.service;

import com.company.project.core.Result;

import java.util.List;
import java.util.Map;

public interface SegmentService {

    /**
     * 根据id获取项目信息
     *
     * @return
     */
    Result getTabInfoById(Map map);

    /**
     * 根据线路，上下行找区间
     */
    List getSegmentByLine(Map map);

    /**
     * 根据区间id获取里程
     *
     * @param id
     * @return
     */
    List getMeterBySegment(String id);

    /**
     * 根据区间id获取环号
     *
     * @param id
     * @return
     */
    List getDuctBySegment(String id);

    Map getSectionInfo(Map map);

    Map getSLInitInfo(Map params);

    Map getPtdInitInfo(Map params);

    Object getPtdDataByName(Map params);

    Object getSlByName(Map params);

    Map getSegmentStatis(Map params);

    /**
     * 区间页面字典数据
     *
     * @param params
     * @return
     */
    Map getDict(Map params);

    Map getStatus(Map params);

    /***
     * 获取区间起始结束环，病害信息
     * @param params
     * @return
     */
    Map getDimDate(Map params);

    /**
     * 大修数据
     *
     * @param params
     * @return
     */
    List getEmphasisList(Map params);

    /***
     * 获取折线图时间初始化数据
     * @param params
     * @return
     */
    Map getCharInitTime(Map params);

    /***
     * 获取管片详情
     * @param params
     * @return
     */
    Map getXiangQing(Map params);
}
