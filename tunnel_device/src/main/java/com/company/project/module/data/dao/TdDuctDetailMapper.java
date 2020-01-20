package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.TdDuctDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TdDuctDetailMapper extends Mapper<TdDuctDetail> {
    void updateByCon(TdDuctDetail duct);

    List<Map> getDiseBySegment(Map map);

    List<Map> getSegmentStatis(@Param("segment") String segment);


    void batchSaveProject(List<TdDuctDetail> subList);

    void updateWeixiu(Map param);
}