package com.company.project.module.data.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@Table(name = "J302_TD_EMPHASIS_INFO")
public class TdEmphasis {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "LINE_UUID")
    private String lineUuid;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "DUCT_CODE")
    private Integer ductCode;

    @Column(name = "MILEAGE_CODE")
    private BigDecimal mileageCode;

    @Column(name = "SEGMENT_UUID")
    private String segmentUuid;

    @Column(name = "SEGMENT_NAME")
    private String segmentName;

    @Column(name = "OPERATE_COMPANY")
    private String operateCompany;

    @Column(name = "OPERATE_TIME")
    private String operateTime;

    @Column(name = "INSERT_TIME")
    private Date insertTime;

    @Column(name = "MAINTAIN_TECH")
    private String maintainTech;

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
     * @return LINE_UUID
     */
    public String getLineUuid() {
        return lineUuid;
    }

    /**
     * @param lineUuid
     */
    public void setLineUuid(String lineUuid) {
        this.lineUuid = lineUuid;
    }

    /**
     * @return UPDOWN
     */
    public String getUpdown() {
        return updown;
    }

    /**
     * @param updown
     */
    public void setUpdown(String updown) {
        this.updown = updown;
    }

    /**
     * @return DUCT_CODE
     */
    public Integer getDuctCode() {
        return ductCode;
    }

    /**
     * @param ductCode
     */
    public void setDuctCode(Integer ductCode) {
        this.ductCode = ductCode;
    }

    /**
     * @return MILEAGE_CODE
     */
    public BigDecimal getMileageCode() {
        return mileageCode;
    }

    /**
     * @param mileageCode
     */
    public void setMileageCode(BigDecimal mileageCode) {
        this.mileageCode = mileageCode;
    }

    /**
     * @return SEGMENT_UUID
     */
    public String getSegmentUuid() {
        return segmentUuid;
    }

    /**
     * @param segmentUuid
     */
    public void setSegmentUuid(String segmentUuid) {
        this.segmentUuid = segmentUuid;
    }

    /**
     * @return SEGMENT_NAME
     */
    public String getSegmentName() {
        return segmentName;
    }

    /**
     * @param segmentName
     */
    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    /**
     * @return OPERATE_COMPANY
     */
    public String getOperateCompany() {
        return operateCompany;
    }

    /**
     * @param operateCompany
     */
    public void setOperateCompany(String operateCompany) {
        this.operateCompany = operateCompany;
    }

    /**
     * @return OPERATE_TIME
     */
    public String getOperateTime() {
        return operateTime;
    }

    /**
     * @param operateTime
     */
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * @return INSERT_TIME
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * @return MAINTAIN_TECH
     */
    public String getMaintainTech() {
        return maintainTech;
    }

    /**
     * @param maintainTech
     */
    public void setMaintainTech(String maintainTech) {
        this.maintainTech = maintainTech;
    }
}