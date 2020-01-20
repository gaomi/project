package com.company.project.module.data.dao;

import com.company.project.core.Mapper;
import com.company.project.module.data.model.Newpoints;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TdPtdMapper extends Mapper<Newpoints> {
    /**
     * 查询所有
     *
     * @return
     */
    List<Newpoints> listAll();

    /**
     * 通过主键pointsGuid获取数据
     *
     * @param pointsGuid
     * @return
     */
    Newpoints getPtdByPointsGuid(@Param("pointsGuid") String pointsGuid);

    List<Map> getSubValue(
        @Param("monitordate") String monitordate,
        @Param("monitordate2") String monitordate2,
        @Param("linecode") String linecode,
        @Param("updown") int updown,
        @Param("pointsGuid") String pointsGuid);
}