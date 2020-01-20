package com.company.project.module.job.util;

import com.company.project.core.util.SpringContextUtils;
import com.company.project.module.job.model.JobInfo;
import com.company.project.module.job.model.JobLog;
import com.company.project.module.job.service.JobLogService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 定时任务
 *
 * @author Chen
 * @created 2019-05-28-16:05.
 */
public class ScheduleJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobInfo scheduleJob = (JobInfo) context.getMergedJobDataMap().get(JobInfo.JOB_PARAM_KEY);

        // 获取spring bean
        //JobLogService scheduleJobLogService = (JobLogService) SpringContextUtils.getBean("JobLogService");
        JobLogService scheduleJobLogService = SpringContextUtils.getBean(JobLogService.class);
        JobLog log = new JobLog();
        log.setLogId(UUID.randomUUID().toString());
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
            logger.info("任务准备执行，任务ID：{}", scheduleJob.getJobId()+"--"+service.hashCode());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);
            Object o = future.get();
            if(o == null || o.toString().length() < 1 ){
                long times = System.currentTimeMillis() - startTime;
                log.setTimes(new BigDecimal(times));
                // 任务状态 0：成功 1：失败
                log.setStatus(0);
                logger.info("任务执行完毕，任务ID：{} 总共耗时：{} 毫秒", scheduleJob.getJobId(), times);
            }else{
                logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId());
                long times = System.currentTimeMillis() - startTime;
                log.setTimes(new BigDecimal(times));
                // 任务状态 0：成功 1：失败
                log.setStatus(1);
                log.setError(StringUtils.substring(o.toString(), 0, 2000));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId());
            long times = System.currentTimeMillis() - startTime;
            log.setTimes(new BigDecimal(times));
            // 任务状态 0：成功 1：失败
            log.setStatus(1);
            log.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.save(log);
            service.shutdown();
        }
    }
}
