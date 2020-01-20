package com.company.project.module.data.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ToString
@Table(name = "J302_TD_DEVICE_LEVEL")
public class DeviceLevel implements Serializable {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "LEVEL1")
    private String level1;

    @Column(name = "LEVEL2")
    private String level2;

    @Column(name = "LEVEL3")
    private String level3;

    @Column(name = "LEVEL4")
    private String level4;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CODE1")
    private String code1;

    @Column(name = "CODE2")
    private String code2;

    @Column(name = "CODE3")
    private String code3;

    @Column(name = "CODE4")
    private String code4;

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
     * @return LEVEL1
     */
    public String getLevel1() {
        return level1;
    }

    /**
     * @param level1
     */
    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    /**
     * @return LEVEL2
     */
    public String getLevel2() {
        return level2;
    }

    /**
     * @param level2
     */
    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    /**
     * @return LEVEL3
     */
    public String getLevel3() {
        return level3;
    }

    /**
     * @param level3
     */
    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    /**
     * @return LEVEL4
     */
    public String getLevel4() {
        return level4;
    }

    /**
     * @param level4
     */
    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    /**
     * @return STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return CODE1
     */
    public String getCode1() {
        return code1;
    }

    /**
     * @param code1
     */
    public void setCode1(String code1) {
        this.code1 = code1;
    }

    /**
     * @return CODE2
     */
    public String getCode2() {
        return code2;
    }

    /**
     * @param code2
     */
    public void setCode2(String code2) {
        this.code2 = code2;
    }

    /**
     * @return CODE3
     */
    public String getCode3() {
        return code3;
    }

    /**
     * @param code3
     */
    public void setCode3(String code3) {
        this.code3 = code3;
    }

    /**
     * @return CODE4
     */
    public String getCode4() {
        return code4;
    }

    /**
     * @param code4
     */
    public void setCode4(String code4) {
        this.code4 = code4;
    }
}