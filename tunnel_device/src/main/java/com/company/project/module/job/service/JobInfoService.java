package com.company.project.module.job.service;

import com.company.project.core.Service;
import com.company.project.module.job.model.JobInfo;

import java.util.List;


/**
 * Created by paodingsoft.chen on 2019/05/28.
 */
public interface JobInfoService extends Service<JobInfo> {

    List<JobInfo> findAllJobs(JobInfo job);

    void addJob(JobInfo job);

    void updateJob(JobInfo job);

    void deleteBatch(String jobIds);

    int updateBatch(String jobIds, String status);

    void run(String jobIds);

    void pause(String jobIds);

    void resume(String jobIds);

    List<JobInfo> getSysCronClazz(JobInfo job);
}
