package com.company.project.module.inspect.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EamWorkOrderService {
    /**
     * 获取eam工单数据
     *
     * @param params
     * @return
     */
    List selectWorkOrderList(Map params);

    /**
     * 根据工单获取病害
     *
     * @param uuid
     * @param lineCode
     * @return
     */
    List<Map> getFaultByWorkOrder(String uuid, String lineCode);

    /**
     * 关闭eam工单
     *  @param orderId
     * @param date
     * @param personNo
     */
    void closeEamOrder(String orderId, Date date, String personNo) throws Exception;
}
