package com.company.project.module.inspect.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ToString
@Table(name = "J302_SI_DEVICE_FAULT_TYPE")
public class DeviceFaultType implements Serializable {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "LEVEL3")
    private String level3;

    @Column(name = "LEVEL4")
    private String level4;

    @Column(name = "DEVICE_TYPE")
    private String deviceType;

    @Column(name = "FAULT_TYPE1")
    private String faultType1;

    @Column(name = "FAULT_TYPE2")
    private String faultType2;

    @Column(name = "FAULT_TYPE3")
    private String faultType3;

    @Column(name = "FAULT_GRADE")
    private String faultGrade;

    @Column(name = "OPT_DESC")
    private String optDesc;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "TYPE_DESC")
    private String typeDesc;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DEVICE_LEVEL_CODE")
    private String deviceLevelCode;

    @Column(name = "DEVICE_TYPE_CODE")
    private String deviceTypeCode;

    @Column(name = "FAULT_TYPE1_CODE")
    private String faultType1Code;

    @Column(name = "FAULT_TYPE2_CODE")
    private String faultType2Code;

    @Column(name = "FAULT_TYPE3_CODE")
    private String faultType3Code;

    @Column(name = "DEVICE_LEVEL_UUID")
    private String deviceLevelUuid;

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
     * @return DEVICE_TYPE
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return FAULT_TYPE1
     */
    public String getFaultType1() {
        return faultType1;
    }

    /**
     * @param faultType1
     */
    public void setFaultType1(String faultType1) {
        this.faultType1 = faultType1;
    }

    /**
     * @return FAULT_TYPE2
     */
    public String getFaultType2() {
        return faultType2;
    }

    /**
     * @param faultType2
     */
    public void setFaultType2(String faultType2) {
        this.faultType2 = faultType2;
    }

    /**
     * @return FAULT_TYPE3
     */
    public String getFaultType3() {
        return faultType3;
    }

    /**
     * @param faultType3
     */
    public void setFaultType3(String faultType3) {
        this.faultType3 = faultType3;
    }

    /**
     * @return FAULT_GRADE
     */
    public String getFaultGrade() {
        return faultGrade;
    }

    /**
     * @param faultGrade
     */
    public void setFaultGrade(String faultGrade) {
        this.faultGrade = faultGrade;
    }

    /**
     * @return OPT_DESC
     */
    public String getOptDesc() {
        return optDesc;
    }

    /**
     * @param optDesc
     */
    public void setOptDesc(String optDesc) {
        this.optDesc = optDesc;
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
     * @return TYPE_DESC
     */
    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * @param typeDesc
     */
    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
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
     * @return DEVICE_LEVEL_CODE
     */
    public String getDeviceLevelCode() {
        return deviceLevelCode;
    }

    /**
     * @param deviceLevelCode
     */
    public void setDeviceLevelCode(String deviceLevelCode) {
        this.deviceLevelCode = deviceLevelCode;
    }

    /**
     * @return DEVICE_TYPE_CODE
     */
    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    /**
     * @param deviceTypeCode
     */
    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    /**
     * @return FAULT_TYPE1_CODE
     */
    public String getFaultType1Code() {
        return faultType1Code;
    }

    /**
     * @param faultType1Code
     */
    public void setFaultType1Code(String faultType1Code) {
        this.faultType1Code = faultType1Code;
    }

    /**
     * @return FAULT_TYPE2_CODE
     */
    public String getFaultType2Code() {
        return faultType2Code;
    }

    /**
     * @param faultType2Code
     */
    public void setFaultType2Code(String faultType2Code) {
        this.faultType2Code = faultType2Code;
    }

    /**
     * @return FAULT_TYPE3_CODE
     */
    public String getFaultType3Code() {
        return faultType3Code;
    }

    /**
     * @param faultType3Code
     */
    public void setFaultType3Code(String faultType3Code) {
        this.faultType3Code = faultType3Code;
    }

    /**
     * @return DEVICE_LEVEL_UUID
     */
    public String getDeviceLevelUuid() {
        return deviceLevelUuid;
    }

    /**
     * @param deviceLevelUuid
     */
    public void setDeviceLevelUuid(String deviceLevelUuid) {
        this.deviceLevelUuid = deviceLevelUuid;
    }
}