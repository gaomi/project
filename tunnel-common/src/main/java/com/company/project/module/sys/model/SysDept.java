package com.company.project.module.sys.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ToString
@Table(name = "J302_TD_SYS_DEPT")
public class SysDept implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "DEPT_LEVEL")
    private String deptLevel;

    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "REMARK")
    private String remark;

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
     * @return PARENT_ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return DEPT_LEVEL
     */
    public String getDeptLevel() {
        return deptLevel;
    }

    /**
     * @param deptLevel
     */
    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    /**
     * @return SEQ
     */
    public Long getSeq() {
        return seq;
    }

    /**
     * @param seq
     */
    public void setSeq(Long seq) {
        this.seq = seq;
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