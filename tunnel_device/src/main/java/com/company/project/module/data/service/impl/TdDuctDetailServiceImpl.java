package com.company.project.module.data.service.impl;

import com.company.project.module.data.dao.TdDuctDetailMapper;
import com.company.project.module.data.model.TdDuctDetail;
import com.company.project.module.data.service.TdDuctDetailService;
import com.company.project.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;


/**
 * Created by paodingsoft.chen on 2019/08/12.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdDuctDetailServiceImpl extends AbstractService<TdDuctDetail> implements TdDuctDetailService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdDuctDetailMapper tdDuctDetailMapper;

    public void updateByCon(TdDuctDetail duct){
        tdDuctDetailMapper.updateByCon(duct);
    }


}
