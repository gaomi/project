package com.company.project.module.sys.dao;

import com.company.project.core.Mapper;
import com.company.project.module.sys.model.SysLog;

public interface SysLogMapper extends Mapper<SysLog> {
    /**
     * 清空
     */
    void deleteAllLogs();


}