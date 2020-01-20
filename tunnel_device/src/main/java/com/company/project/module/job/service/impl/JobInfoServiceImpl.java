package com.company.project.module.job.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.annotation.CronTag;
import com.company.project.module.job.dao.JobInfoMapper;
import com.company.project.module.job.model.JobInfo;
import com.company.project.module.job.service.JobInfoService;
import com.company.project.module.job.util.ScheduleUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by paodingsoft.chen on 2019/05/28.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobInfoServiceImpl extends AbstractService<JobInfo> implements JobInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private JobInfoMapper jobInfoMapper;
    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<JobInfo> scheduleJobList = this.jobInfoMapper.selectAll();
        // 如果不存在，则创建
        scheduleJobList.forEach(scheduleJob -> {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        });
    }


    @Override
    public List<JobInfo> findAllJobs(JobInfo job) {
        try {
            Example example = new Example(JobInfo.class);
            Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(job.getBeanName())) {
                criteria.andCondition("bean_name=", job.getBeanName());
            }
            if (StringUtils.isNotBlank(job.getMethodName())) {
                criteria.andCondition("method_name=", job.getMethodName());
            }
            if (null != job.getStatus()) {
                criteria.andCondition("status=", Long.valueOf(job.getStatus()));
            }
            example.setOrderByClause("job_id");
            return this.findByExample(example);
        } catch (Exception e) {
            logger.error("获取任务失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void addJob(JobInfo job) {
        job.setCreateTime(new Date());
        int pauseVal = Integer.parseInt(JobInfo.ScheduleStatus.PAUSE.getValue());
        job.setStatus(pauseVal);
        this.save(job);
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    @Override
    @Transactional
    public void updateJob(JobInfo job) {
        ScheduleUtils.updateScheduleJob(scheduler, job);
        this.updateNotNull(job);
    }

    @Override
    @Transactional
    public void deleteBatch(String jobIds) {
        List<String> list = Arrays.asList(jobIds.split(","));
        list.forEach(jobId -> ScheduleUtils.deleteScheduleJob(scheduler, jobId));
        this.batchDelete(jobIds, "jobId", JobInfo.class);
    }

    @Override
    @Transactional
    public int updateBatch(String jobIds, String status) {
        List<String> list = Arrays.asList(jobIds.split(","));
        Example example = new Example(JobInfo.class);
        example.createCriteria().andIn("jobId", list);
        JobInfo job = new JobInfo();
        job.setStatus(Integer.parseInt(status));
        return this.jobInfoMapper.updateByExampleSelective(job, example);
    }

    @Override
    @Transactional
    public void run(String jobIds) {
        String[] list = jobIds.split(",");
        Arrays.stream(list).forEach(jobId -> ScheduleUtils.run(scheduler, this.findById(jobId)));
    }

    @Override
    @Transactional
    public void pause(String jobIds) {
        String[] list = jobIds.split(",");
        Arrays.stream(list).forEach(jobId -> ScheduleUtils.pauseJob(scheduler, jobId));
        this.updateBatch(jobIds, JobInfo.ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional
    public void resume(String jobIds) {
        String[] list = jobIds.split(",");
        Arrays.stream(list).forEach(jobId -> ScheduleUtils.resumeJob(scheduler, jobId));
        this.updateBatch(jobIds, JobInfo.ScheduleStatus.NORMAL.getValue());
    }

    @Override
    public List<JobInfo> getSysCronClazz(JobInfo job) {
        Reflections reflections = new Reflections("com.company.project.module.job.task");
        List<JobInfo> jobList = new ArrayList<>();
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(CronTag.class);

        for (Class cls : annotated) {
            String clsSimpleName = cls.getSimpleName();
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                JobInfo job1 = new JobInfo();
                String methodName = method.getName();
                Parameter[] methodParameters = method.getParameters();
                String params = String.format("%s(%s)", methodName, Arrays.stream(methodParameters).map(item -> item.getType().getSimpleName() + " " + item.getName()).collect(Collectors.joining(", ")));

                job1.setBeanName(StringUtils.uncapitalize(clsSimpleName));
                job1.setMethodName(methodName);
                job1.setParams(params);
                jobList.add(job1);
            }
        }
        return jobList;
    }
}
