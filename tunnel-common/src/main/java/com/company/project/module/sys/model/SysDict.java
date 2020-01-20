package com.company.project.module.sys.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Table(name = "J302_TD_SYS_DICT")
public class SysDict {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "PID")
    private String pid;

    @Column(name = "KEY")
    private String key;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "FIELD_NAME")
    private String fieldName;

    @Column(name = "TABLE_NAME")
    private String tableName;

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
     * @return VALUE
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
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
     * @return KEY
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
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
     * @return FIELD_NAME
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return TABLE_NAME
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}