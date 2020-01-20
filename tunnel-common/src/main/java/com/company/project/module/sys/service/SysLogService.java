package com.company.project.module.sys.service;

import com.company.project.core.Service;
import com.company.project.module.sys.model.SysLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;


/**
 * Created by chen on 2019/05/21.
 */
public interface SysLogService extends Service<SysLog> {

    List<SysLog> findAllLogs(SysLog log);

    @Async
    void saveLog(ProceedingJoinPoint point, SysLog log) throws JsonProcessingException;

    /**
     * 根据条件查询
     *
     * @param map
     * @return
     */
    List<SysLog> findAllLogs(Map map);

    /**
     * 清空日志
     */
    void deleteAll();
}
