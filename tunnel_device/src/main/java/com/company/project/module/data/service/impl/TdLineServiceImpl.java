package com.company.project.module.data.service.impl;

import com.company.project.module.data.dao.TdLineMapper;
import com.company.project.module.data.model.TdLine;
import com.company.project.module.data.service.TdLineService;
import com.company.project.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by paodingsoft.chen on 2019/08/07.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdLineServiceImpl extends AbstractService<TdLine> implements TdLineService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdLineMapper tdLineMapper;

    @Override
    public List getLineList() {
        Condition con = new Condition(TdLine.class);
        con.createCriteria().andIsNotNull("lineName");
        con.orderBy("lineCode");
        return tdLineMapper.selectByCondition(con);
    }
}
