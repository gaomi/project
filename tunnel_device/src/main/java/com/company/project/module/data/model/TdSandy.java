package com.company.project.module.data.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "J302_TD_SANDY_INFO")
public class TdSandy {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "SANDY_ID")
    private String sandyId;

    @Column(name = "SOIL_LEVEL")
    private String soilLevel;

    @Column(name = "SANDY_TUNNEL_POSITION")
    private String sandyTunnelPosition;

    @Column(name = "LINE_CODE")
    private String lineCode;

    @Column(name = "LINE_UUID")
    private String lineUuid;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "START_STATION_UUID")
    private String startStationUuid;

    @Column(name = "START_STATION_CODE")
    private String startStationCode;

    @Column(name = "START_DUCT_CODE")
    private Long startDuctCode;

    @Column(name = "END_STATION_UUID")
    private String endStationUuid;

    @Column(name = "END_STATION_CODE")
    private String endStationCode;

    @Column(name = "END_DUCT_CODE")
    private Long endDuctCode;

    @Column(name = "SEGMENT_NAME")
    private String segmentName;

    @Column(name = "SEGMENT_UUID")
    private String segmentUuid;

    @Column(name = "START_FLAG")
    private String startFlag;

    @Column(name = "START_MILEAGE")
    private BigDecimal startMileage;

    @Column(name = "END_FLAG")
    private String endFlag;

    @Column(name = "END_MILEAGE")
    private BigDecimal endMileage;

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
     * @return SANDY_ID
     */
    public String getSandyId() {
        return sandyId;
    }

    /**
     * @param sandyId
     */
    public void setSandyId(String sandyId) {
        this.sandyId = sandyId;
    }

    /**
     * @return SOIL_LEVEL
     */
    public String getSoilLevel() {
        return soilLevel;
    }

    /**
     * @param soilLevel
     */
    public void setSoilLevel(String soilLevel) {
        this.soilLevel = soilLevel;
    }

    /**
     * @return SANDY_TUNNEL_POSITION
     */
    public String getSandyTunnelPosition() {
        return sandyTunnelPosition;
    }

    /**
     * @param sandyTunnelPosition
     */
    public void setSandyTunnelPosition(String sandyTunnelPosition) {
        this.sandyTunnelPosition = sandyTunnelPosition;
    }

    /**
     * @return LINE_CODE
     */
    public String getLineCode() {
        return lineCode;
    }

    /**
     * @param lineCode
     */
    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
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
     * @return START_STATION_UUID
     */
    public String getStartStationUuid() {
        return startStationUuid;
    }

    /**
     * @param startStationUuid
     */
    public void setStartStationUuid(String startStationUuid) {
        this.startStationUuid = startStationUuid;
    }

    /**
     * @return START_STATION_CODE
     */
    public String getStartStationCode() {
        return startStationCode;
    }

    /**
     * @param startStationCode
     */
    public void setStartStationCode(String startStationCode) {
        this.startStationCode = startStationCode;
    }

    /**
     * @return END_STATION_UUID
     */
    public String getEndStationUuid() {
        return endStationUuid;
    }

    /**
     * @param endStationUuid
     */
    public void setEndStationUuid(String endStationUuid) {
        this.endStationUuid = endStationUuid;
    }

    /**
     * @return END_STATION_CODE
     */
    public String getEndStationCode() {
        return endStationCode;
    }

    /**
     * @param endStationCode
     */
    public void setEndStationCode(String endStationCode) {
        this.endStationCode = endStationCode;
    }

    public Long getStartDuctCode() {
        return startDuctCode;
    }

    public void setStartDuctCode(Long startDuctCode) {
        this.startDuctCode = startDuctCode;
    }

    public Long getEndDuctCode() {
        return endDuctCode;
    }

    public void setEndDuctCode(Long endDuctCode) {
        this.endDuctCode = endDuctCode;
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
     * @return START_FLAG
     */
    public String getStartFlag() {
        return startFlag;
    }

    /**
     * @param startFlag
     */
    public void setStartFlag(String startFlag) {
        this.startFlag = startFlag;
    }

    /**
     * @return START_MILEAGE
     */
    public BigDecimal getStartMileage() {
        return startMileage;
    }

    /**
     * @param startMileage
     */
    public void setStartMileage(BigDecimal startMileage) {
        this.startMileage = startMileage;
    }

    /**
     * @return END_FLAG
     */
    public String getEndFlag() {
        return endFlag;
    }

    /**
     * @param endFlag
     */
    public void setEndFlag(String endFlag) {
        this.endFlag = endFlag;
    }

    /**
     * @return END_MILEAGE
     */
    public BigDecimal getEndMileage() {
        return endMileage;
    }

    /**
     * @param endMileage
     */
    public void setEndMileage(BigDecimal endMileage) {
        this.endMileage = endMileage;
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