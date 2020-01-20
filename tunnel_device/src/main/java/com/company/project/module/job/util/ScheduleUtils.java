package com.company.project.module.job.util;

import com.company.project.module.job.model.JobInfo;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务工具类
 *
 * @author Chen
 * @created 2019-05-28-15:57.
 */
public class ScheduleUtils {
    private static Logger logger = LoggerFactory.getLogger(ScheduleUtils.class);

    protected ScheduleUtils() {

    }

    private static final String JOB_NAME = "TASK_";

    /**
     * 获取触发器key
     */
    private static TriggerKey getTriggerKey(String jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    private static JobKey getJobKey(String jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            logger.error("获取Cron表达式失败", e);
        }
        return null;
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, JobInfo scheduleJob) {
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(scheduleJob.getJobId()))
                    .build();

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getJobId()))
                    .withSchedule(scheduleBuilder).build();

            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(JobInfo.JOB_PARAM_KEY, scheduleJob);

            scheduler.scheduleJob(jobDetail, trigger);
            String status = scheduleJob.getStatus() == null ? "" : scheduleJob.getStatus().toString();
            // 暂停任务
            if (status.equals(JobInfo.ScheduleStatus.PAUSE.getValue())) {
                pauseJob(scheduler, scheduleJob.getJobId());
            }
            logger.error("创建定时任务成功:jobId=" + scheduleJob.getJobId());
        } catch (SchedulerException e) {
            logger.error("创建定时任务失败", e);
        }
    }

    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, JobInfo scheduleJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobId());

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getJobId());

            if (trigger == null) {
                throw new SchedulerException("获取Cron表达式失败");
            } else {
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                // 参数
                trigger.getJobDataMap().put(JobInfo.JOB_PARAM_KEY, scheduleJob);
            }

            scheduler.rescheduleJob(triggerKey, trigger);

            String status = scheduleJob.getStatus() == null ? "" : scheduleJob.getStatus().toString();
            // 暂停任务
            if (status.equals(JobInfo.ScheduleStatus.PAUSE.getValue())) {
                pauseJob(scheduler, scheduleJob.getJobId());
            }
            logger.error("更新定时任务成功:jobId=" + scheduleJob.getJobId());
        } catch (SchedulerException e) {
            logger.error("更新定时任务失败", e);
        }
    }

    /**
     * 立即执行任务
     */
    public static void run(Scheduler scheduler, JobInfo scheduleJob) {
        try {
            // 参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(JobInfo.JOB_PARAM_KEY, scheduleJob);

            scheduler.triggerJob(getJobKey(scheduleJob.getJobId()), dataMap);
            logger.error("启动定时任务成功：jobId=" + scheduleJob.getJobId() + " , method= " + scheduleJob.getMethodName());
        } catch (SchedulerException e) {
            logger.error("执行定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
            logger.error("暂停定时任务成功:jobId=" + jobId);
        } catch (SchedulerException e) {
            logger.error("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
            logger.error("恢复定时任务成功:jobId=" + jobId);
        } catch (SchedulerException e) {
            logger.error("恢复定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
            logger.error("删除定时任务成功:jobId=" + jobId);
        } catch (SchedulerException e) {
            logger.error("删除定时任务失败", e);
        }
    }
}
