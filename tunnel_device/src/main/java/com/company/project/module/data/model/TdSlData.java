package com.company.project.module.data.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@Table(name = "J302_TD_SL_DATA")
public class TdSlData {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "LINE_UUID")
    private String lineUuid;

    @Column(name = "DUCT_UUID")
    private String ductUuid;

    @Column(name = "RECODE_DATE")
    private Date recodeDate;

    @Column(name = "RECODE_VALUE")
    private BigDecimal recodeValue;

    @Column(name = "IS_DENSITY")
    private String isDensity;

    @Column(name = "SEGMENT_UUID")
    private String segmentUuid;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "FLB")
    private String flb;

    @Column(name = "GH")
    private String gh;

    @Column(name = "BDQK")
    private String bdqk;

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
     * @return DUCT_UUID
     */
    public String getDuctUuid() {
        return ductUuid;
    }

    /**
     * @param ductUuid
     */
    public void setDuctUuid(String ductUuid) {
        this.ductUuid = ductUuid;
    }

    /**
     * @return RECODE_DATE
     */
    public Date getRecodeDate() {
        return recodeDate;
    }

    /**
     * @param recodeDate
     */
    public void setRecodeDate(Date recodeDate) {
        this.recodeDate = recodeDate;
    }

    /**
     * @return RECODE_VALUE
     */
    public BigDecimal getRecodeValue() {
        return recodeValue;
    }

    /**
     * @param recodeValue
     */
    public void setRecodeValue(BigDecimal recodeValue) {
        this.recodeValue = recodeValue;
    }

    /**
     * @return IS_DENSITY
     */
    public String getIsDensity() {
        return isDensity;
    }

    /**
     * @param isDensity
     */
    public void setIsDensity(String isDensity) {
        this.isDensity = isDensity;
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
     * @return FLB
     */
    public String getFlb() {
        return flb;
    }

    /**
     * @param flb
     */
    public void setFlb(String flb) {
        this.flb = flb;
    }

    /**
     * @return GH
     */
    public String getGh() {
        return gh;
    }

    /**
     * @param gh
     */
    public void setGh(String gh) {
        this.gh = gh;
    }

    /**
     * @return BDQK
     */
    public String getBdqk() {
        return bdqk;
    }

    /**
     * @param bdqk
     */
    public void setBdqk(String bdqk) {
        this.bdqk = bdqk;
    }
}