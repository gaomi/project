package com.company.project.module.api.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.api.dao.BhInfoMapper;
import com.company.project.module.api.model.BhInfo;
import com.company.project.module.api.service.ApiBhInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/07/08.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ApiBhInfoServiceImpl extends AbstractService<BhInfo> implements ApiBhInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BhInfoMapper bhInfoMapper;

    @Override
    public List<BhInfo> selectAll(Map params) {
        Condition condition = new Condition(BhInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        for (Object key : params.keySet()) {
//            criteria.andEqualTo();
            criteria.andLike((String) key, params.get(key) + "%");
        }
        return this.bhInfoMapper.selectByCondition(condition);
    }
}
