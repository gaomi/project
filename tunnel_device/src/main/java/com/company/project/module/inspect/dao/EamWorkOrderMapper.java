package com.company.project.module.inspect.dao;

import com.company.project.core.Mapper;
import com.company.project.module.inspect.model.EamWorkOrder;

import java.util.List;
import java.util.Map;

public interface EamWorkOrderMapper extends Mapper<EamWorkOrder> {
    /**
     * 修改工单状态
     *
     * @param orderId
     */
    void updateWorkOrderStatus(String orderId);

    /**
     * 根据条件查询工单数据
     *
     * @param params
     * @return
     */
    List selectWorkOrderByV(Map params);
}