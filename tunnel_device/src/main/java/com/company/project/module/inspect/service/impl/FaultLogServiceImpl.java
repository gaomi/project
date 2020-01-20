package com.company.project.module.inspect.service.impl;

import com.company.project.module.inspect.dao.SiFaultDataMapper;
import com.company.project.module.inspect.service.FaultLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @created 2019-11-23-15:50.
 */
@Service
public class FaultLogServiceImpl implements FaultLogService {
    @Autowired
    private SiFaultDataMapper siFaultDataMapper;

    @Override
    public List findAllFaultLog(Map params) {


        return siFaultDataMapper.findAllFaultForWebByV(params);
    }

}
