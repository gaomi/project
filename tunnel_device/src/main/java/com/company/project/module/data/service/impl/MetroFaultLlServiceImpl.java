package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.data.dao.MetroFaultLlMapper;
import com.company.project.module.data.model.MetroFaultLl;
import com.company.project.module.data.service.MetroFaultLlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/25.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MetroFaultLlServiceImpl extends AbstractService<MetroFaultLl> implements MetroFaultLlService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MetroFaultLlMapper metroFaultLlMapper;

    @Override
    public List<MetroFaultLl> getBhLvli(Map params) {
        return metroFaultLlMapper.getBhLvli(params);
    }

    @Override
    public List<Map> getBhBiHuan() {
        return metroFaultLlMapper.getBhBiHuan();
    }
}
