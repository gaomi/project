package com.company.project.module.inspect.service;

import java.util.List;
import java.util.Map;

public interface FaultLogService {
    /**
     * 查询病害记录
     * @param params
     * @return
     */
    List findAllFaultLog(Map params);
}
