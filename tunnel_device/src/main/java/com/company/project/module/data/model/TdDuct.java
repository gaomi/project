package com.company.project.module.data.model;

import lombok.ToString;

import java.math.BigDecimal;
import javax.persistence.*;

@ToString
@Table(name = "J302_TD_DUCT_INFO")
public class TdDuct {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "DUCT_CODE")
    private Integer ductCode;

    @Column(name = "LINE_UUID")
    private String lineUuid;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "SEGMENT_UUID")
    private String segmentUuid;

    @Column(name = "SEGMENT_GROUP")
    private String segmentGroup;

    @Column(name = "MILEAGE_CODE")
    private BigDecimal mileageCode;

    @Column(name = "IS_SECTION")
    private String isSection;

    @Column(name = "SEQ")
    private BigDecimal seq;

    @Column(name = "REMARK")
    private String remark;

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
     * @return SEGMENT_GROUP
     */
    public String getSegmentGroup() {
        return segmentGroup;
    }

    /**
     * @param segmentGroup
     */
    public void setSegmentGroup(String segmentGroup) {
        this.segmentGroup = segmentGroup;
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
     * @return IS_SECTION
     */
    public String getIsSection() {
        return isSection;
    }

    /**
     * @param isSection
     */
    public void setIsSection(String isSection) {
        this.isSection = isSection;
    }

    /**
     * @return SEQ
     */
    public BigDecimal getSeq() {
        return seq;
    }

    /**
     * @param seq
     */
    public void setSeq(BigDecimal seq) {
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
}