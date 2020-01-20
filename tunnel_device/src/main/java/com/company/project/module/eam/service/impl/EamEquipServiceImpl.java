package com.company.project.module.eam.service.impl;

import com.company.project.module.eam.dao.EamEquipMapper;
import com.company.project.module.eam.model.EamEquip;
import com.company.project.module.eam.service.EamEquipService;
import com.company.project.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 * Created by paodingsoft.chen on 2020/01/09.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EamEquipServiceImpl extends AbstractService<EamEquip> implements EamEquipService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private EamEquipMapper eamEquipMapper;

}
