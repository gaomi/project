package com.company.project.module.data.model;

import javax.persistence.*;

@Table(name = "J302_TD_LINE_INFO")
public class TdLine {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "LINE_CODE")
    private String lineCode;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "LINE_NAME")
    private String lineName;

    @Column(name = "START_STATION_CODE")
    private String startStationCode;

    @Column(name = "START_STATION_UUID")
    private String startStationUuid;

    @Column(name = "START_STATION_NAME")
    private String startStationName;

    @Column(name = "END_STATION_CODE")
    private String endStationCode;

    @Column(name = "END_STATION_UUID")
    private String endStationUuid;

    @Column(name = "END_STATION_NAME")
    private String endStationName;

    @Column(name = "LINE_TYPE")
    private String lineType;

    @Column(name = "LINE_PHASE")
    private String linePhase;

    @Column(name = "STATION_COUNT")
    private Integer stationCount;

    @Column(name = "SEGMENT_COUNT")
    private Integer segmentCount;

    @Column(name = "MILEAGE")
    private String mileage;

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
     * @return LINE_NAME
     */
    public String getLineName() {
        return lineName;
    }

    /**
     * @param lineName
     */
    public void setLineName(String lineName) {
        this.lineName = lineName;
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
     * @return START_STATION_NAME
     */
    public String getStartStationName() {
        return startStationName;
    }

    /**
     * @param startStationName
     */
    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
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
     * @return END_STATION_NAME
     */
    public String getEndStationName() {
        return endStationName;
    }

    /**
     * @param endStationName
     */
    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    /**
     * @return LINE_TYPE
     */
    public String getLineType() {
        return lineType;
    }

    /**
     * @param lineType
     */
    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    /**
     * @return LINE_PHASE
     */
    public String getLinePhase() {
        return linePhase;
    }

    /**
     * @param linePhase
     */
    public void setLinePhase(String linePhase) {
        this.linePhase = linePhase;
    }

    /**
     * @return STATION_COUNT
     */
    public Integer getStationCount() {
        return stationCount;
    }

    /**
     * @param stationCount
     */
    public void setStationCount(Integer stationCount) {
        this.stationCount = stationCount;
    }

    /**
     * @return SEGMENT_COUNT
     */
    public Integer getSegmentCount() {
        return segmentCount;
    }

    /**
     * @param segmentCount
     */
    public void setSegmentCount(Integer segmentCount) {
        this.segmentCount = segmentCount;
    }

    /**
     * @return MILEAGE
     */
    public String getMileage() {
        return mileage;
    }

    /**
     * @param mileage
     */
    public void setMileage(String mileage) {
        this.mileage = mileage;
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