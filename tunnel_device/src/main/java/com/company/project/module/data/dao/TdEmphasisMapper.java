package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdEmphasis;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TdEmphasisMapper extends Mapper<TdEmphasis> {
    /**
     * 根据区间查大修的环
     *
     * @param group_segment
     * @return
     */
    List<Map> getEmphasisForMainCharts(@Param("group_segment") List<String> group_segment);

    /***
     * 获取大修信息区间选择
     * @return
     */
    List<Map> getEmphasisSelect();
}