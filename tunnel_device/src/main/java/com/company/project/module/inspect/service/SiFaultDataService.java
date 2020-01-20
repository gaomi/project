package com.company.project.module.inspect.service;

import com.company.project.core.Service;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.inspect.model.FaultPlan;
import com.company.project.module.inspect.model.SiFaultData;

import java.util.List;
import java.util.Map;

public interface SiFaultDataService extends Service<SiFaultData> {
    /**
     * 根据工单编号和线路查找病害
     *
     * @param uuid
     * @param lineCode
     * @return
     */
    List<Map> getFaultByWorkOrder(String uuid, String lineCode);

    /**
     * 根据工单提交审核过的病害
     *
     * @param orderId
     * @param list
     * @param personNo
     */
    void commitCheckFaultByWorkOrder(String orderId, List list, String personNo) throws Exception;

    /***
     * 病害履历查询
     * @param id
     * @return
     */
    List<SiFaultData> findRecord(String id);

    /***
     * 获取病害状态字典
     * @return
     */
    List<TdDict> getFaultStatus();
}
