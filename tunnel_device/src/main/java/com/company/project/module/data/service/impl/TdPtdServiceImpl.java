package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.data.dao.TdCjDataMapper;
import com.company.project.module.data.dao.TdPtdMapper;
import com.company.project.module.data.model.Newpoints;
import com.company.project.module.data.service.TdPtdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/06/28.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdPtdServiceImpl extends AbstractService<Newpoints> implements TdPtdService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdPtdMapper tdPtdMapper;
    @Resource
    private TdCjDataMapper tdCjDataMapper;

    @Override
    public List<Map> listAll(Map map) {
        List<Map> list = tdCjDataMapper.getCjPtd(map);
        return list;
    }

    @Override
    public Newpoints getPtdByPointsGuid(String pointsGuid) {
        Newpoints ptd = tdPtdMapper.getPtdByPointsGuid(pointsGuid);
        return ptd;
    }

    @Override
    public List<Map> getSubValue(String monitordate, String monitordate2, String linecode, int updown, String pointsGuid) {
        return null;
    }


}
