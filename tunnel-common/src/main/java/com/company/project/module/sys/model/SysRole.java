package com.company.project.module.sys.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@ToString
@Table(name = "J302_TD_SYS_ROLE")
public class SysRole {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private Long type;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "OPERATOR")
    private String operator;

    @Column(name = "OPERATE_TIME")
    private Date operateTime;

    @Column(name = "OPERATE_IP")
    private String operateIp;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return TYPE
     */
    public Long getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Long type) {
        this.type = type;
    }

    /**
     * @return STATUS
     */
    public Long getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

    /**
     * @return OPERATOR
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return OPERATE_TIME
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * @param operateTime
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * @return OPERATE_IP
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * @param operateIp
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}