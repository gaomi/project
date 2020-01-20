package com.company.project.module.data.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.Newpoints;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/07/30.
 */
public interface TdPtdService extends Service<Newpoints> {
    /**
     * description 查询旁通道列表
     *
     * @return
     */
    List<Map> listAll(Map map);

    /**
     * 通过主键pointsGuid获取数据
     *
     * @param pointsGuid
     * @return
     */
    Newpoints getPtdByPointsGuid(@Param("pointsGuid") String pointsGuid);

    List<Map> getSubValue(String monitordate, String monitordate2, String linecode, int updown, String pointsGuid);

}
