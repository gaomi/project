package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.data.dao.TdMaintainInfoMapper;
import com.company.project.module.data.model.TdMaintainInfo;
import com.company.project.module.data.service.TdMaintainInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/09/27.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdMaintainInfoServiceImpl extends AbstractService<TdMaintainInfo> implements TdMaintainInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdMaintainInfoMapper tdMaintainInfoMapper;

    @Override
    public void insert(Map map) {
        tdMaintainInfoMapper.insertSeg(map);
    }

    @Override
    public List<TdMaintainInfo> selectByCon(Map params) {
        return tdMaintainInfoMapper.selectByCon(params);
    }

    @Override
    public List<Map> findSegment() {
        return tdMaintainInfoMapper.findSegment();
    }

    @Override
    public List<Map> getDevice(Map map) {
        return tdMaintainInfoMapper.getDevice(map);
    }

    @Override
    public void saveDeviceMainTain(Map result) {
        tdMaintainInfoMapper.saveDeviceMainTain(result);
    }

    @Override
    public List<TdMaintainInfo> selectByDevice(Map params) {
        return tdMaintainInfoMapper.selectByDevice(params);
    }

    @Override
    public void updateFault(Map map) {
        tdMaintainInfoMapper.updateFault(map);
    }
}
