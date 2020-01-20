package com.company.project.module.data.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Table(name = "J302_TD_DICT")
public class TdDict {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "DICT_KEY")
    private String dictKey;

    @Column(name = "DICT_VALUE")
    private String dictValue;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "PID")
    private String pid;

    @Column(name = "SEQ")
    private String seq;

    @Column(name = "FOR_MODULE")
    private String forModule;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "IS_PARENT")
    private String idParent;


    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return DICT_KEY
     */
    public String getDictKey() {
        return dictKey;
    }

    /**
     * @param dictKey
     */
    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    /**
     * @return DICT_VALUE
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * @param dictValue
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
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
     * @return PID
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return SEQ
     */
    public String getSeq() {
        return seq;
    }

    /**
     * @param seq
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }

    /**
     * @return FOR_MODULE
     */
    public String getForModule() {
        return forModule;
    }

    /**
     * @param forModule
     */
    public void setForModule(String forModule) {
        this.forModule = forModule;
    }
}