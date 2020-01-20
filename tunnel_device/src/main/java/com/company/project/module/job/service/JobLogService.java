package com.company.project.module.job.service;

import com.company.project.core.Service;
import com.company.project.module.job.model.JobLog;

import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/05/28.
 */
public interface JobLogService extends Service<JobLog> {
    List<JobLog> findAllJobLogs(JobLog jobLog);

    void saveJobLog(JobLog log);

    void deleteBatch(String jobLogIds);

    /***
     * 清空日志调度
     */
    void deleteJobAllLogs();

    List<JobLog> selectJobLog(Map params);
}