package com.company.project.module.inspect.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.inspect.dao.EamWorkOrderMapper;
import com.company.project.module.inspect.model.EamWorkOrder;
import com.company.project.module.inspect.service.EamWorkOrderService;
import com.company.project.module.inspect.service.SiFaultDataService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-12-14-15:13.
 */
@Service
public class EamWorkOrderServiceImpl extends AbstractService<EamWorkOrder> implements EamWorkOrderService {

    @Autowired
    private SiFaultDataService siFaultDataService;
    @Autowired
    private EamWorkOrderMapper eamWorkOrderMapper;

    @Override
    public List selectWorkOrderList(Map params) {
        return eamWorkOrderMapper.selectWorkOrderByV(params);
    }

    @Override
    public List<Map> getFaultByWorkOrder(String uuid, String lineCode) {
        List<Map> checkFault = siFaultDataService.getFaultByWorkOrder(uuid, lineCode);
        return checkFault;
    }

    @Override
    public void closeEamOrder(String orderId, Date date, String personNo) throws Exception {
        //修改本地工单状态
        eamWorkOrderMapper.updateWorkOrderStatus(orderId);
        //TODO 调取EAM关闭工单接口

        throw new RuntimeException();

    }


}
