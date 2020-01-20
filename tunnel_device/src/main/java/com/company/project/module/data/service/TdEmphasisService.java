package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.TdEmphasis;
import com.company.project.module.data.model.TdLine;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/07.
 */
public interface TdEmphasisService extends Service<TdEmphasis> {

    /***
     * 获取大修信息列表
     * @param params
     * @return
     */
    List getEmphasisList(Map params);

    /***
     * 获取区间内所有大修信息
     * @param group_segment
     * @return
     */
    List<Map> getEmphasisForMainCharts(List group_segment);

    /***
     * 获取风井区间
     * @param params
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
     * 获取大修信息区间选择
     * @return
     */
    List<Map> getEmphasisSelect();
}
