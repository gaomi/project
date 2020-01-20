package com.company.project.module.job.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.module.job.dao.JobLogMapper;
import com.company.project.module.job.model.JobLog;
import com.company.project.module.job.service.JobLogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by paodingsoft.chen on 2019/05/28.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobLogServiceImpl extends AbstractService<JobLog> implements JobLogService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private JobLogMapper jobLogMapper;

    @Override
    public List<JobLog> findAllJobLogs(JobLog jobLog) {
        try {
            Example example = new Example(JobLog.class);
            Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(jobLog.getBeanName())) {
                criteria.andCondition("bean_name=", jobLog.getBeanName());
            }
            if (StringUtils.isNotBlank(jobLog.getMethodName())) {
                criteria.andCondition("method_name=", jobLog.getMethodName());
            }
            if (null != jobLog.getStatus()) {
                criteria.andCondition("status=", Long.valueOf(jobLog.getStatus()));
            }
            example.setOrderByClause("log_id desc");
            return this.findByExample(example);
        } catch (Exception e) {
            logger.error("获取调度日志信息失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void saveJobLog(JobLog log) {
        log.setLogId(UUID.randomUUID().toString());
        this.save(log);
    }

    @Override
    @Transactional
    public void deleteBatch(String jobLogIds) {
        List<String> list = Arrays.asList(jobLogIds.split(","));
        this.batchDelete(jobLogIds, "logId", JobLog.class);
    }

    @Override
    public void deleteJobAllLogs() {
        Condition con  = new Condition(JobLog.class);
        jobLogMapper.deleteByCondition(con);
    }

    @Override
    public List<JobLog> selectJobLog(Map params) {
        Condition con  = new Condition(JobLog.class);
        Criteria criteria = con.createCriteria();
        if(params.containsKey("keyWord") && params.get("keyWord").toString().length() > 0){
            criteria.andLike("methodName",params.get("keyWord").toString());
        }
        if(params.containsKey("sCreateTime") && params.get("sCreateTime").toString().length() > 0){
            criteria.andCondition("to_char(CREATE_TIME,'YYYY-MM-dd') >",params.get("sCreateTime").toString());
        }
        if(params.containsKey("eCreateTime") && params.get("eCreateTime").toString().length() > 0){
            criteria.andCondition("to_char(CREATE_TIME,'YYYY-MM-dd') <",params.get("eCreateTime").toString());
        }
        return jobLogMapper.selectByCondition(con);
    }
}

