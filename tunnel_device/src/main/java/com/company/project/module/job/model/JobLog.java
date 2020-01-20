package com.company.project.module.job.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "J302_TD_SYS_JOB_LOG")
public class JobLog implements Serializable {
    @Id
    @Column(name = "LOG_ID")
    private String logId;

    @Column(name = "JOB_ID")
    private String jobId;

    @Column(name = "BEAN_NAME")
    private String beanName;

    @Column(name = "METHOD_NAME")
    private String methodName;

    @Column(name = "PARAMS")
    private String params;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "ERROR")
    private String error;

    @Column(name = "TIMES")
    private BigDecimal times;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * @return LOG_ID
     */
    public String getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * @return JOB_ID
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * @param jobId
     */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * @return BEAN_NAME
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * @param beanName
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * @return METHOD_NAME
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * @return PARAMS
     */
    public String getParams() {
        return params;
    }

    /**
     * @param params
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * @return STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return ERROR
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return TIMES
     */
    public BigDecimal getTimes() {
        return times;
    }

    /**
     * @param times
     */
    public void setTimes(BigDecimal times) {
        this.times = times;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}