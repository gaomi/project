package com.company.project.module.data.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@ToString
@Table(name = "J302_TD_STATION_INFO")
public class TdStation implements Serializable {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "STATION_CODE")
    private String stationCode;

    @Column(name = "LINE_CODE")
    private String lineCode;

    @Column(name = "STATION_NAME")
    private String stationName;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "START_FLAG")
    private String startFlag;

    @Column(name = "START_FLAG_TYPE")
    private String startFlagType;

    @Column(name = "START_MILEAGE")
    private BigDecimal startMileage;

    @Column(name = "END_FLAG")
    private String endFlag;

    @Column(name = "END_FLAG_TYPE")
    private String endFlagType;

    @Column(name = "END_MILEAGE")
    private BigDecimal endMileage;

    @Column(name = "UP_OUT_MILEAGE")
    private BigDecimal upOutMileage;

    @Column(name = "UP_IN_MILEAGE")
    private BigDecimal upInMileage;

    @Column(name = "UP_MIDDLE_MILEAGE")
    private BigDecimal upMiddleMileage;

    @Column(name = "UP_OUT_DUCT")
    private Integer upOutDuct;

    @Column(name = "UP_IN_DUCT")
    private Integer upInDuct;

    @Column(name = "UP_MIDDLE_DUCT")
    private Integer upMiddleDuct;

    @Column(name = "DOWN_OUT_MILEAGE")
    private BigDecimal downOutMileage;

    @Column(name = "DOWN_IN_MILEAGE")
    private BigDecimal downInMileage;

    @Column(name = "DOWN_MIDDLE_MILEAGE")
    private BigDecimal downMiddleMileage;

    @Column(name = "DOWN_OUT_DUCT")
    private Integer downOutDuct;

    @Column(name = "DOWN_IN_DUCT")
    private Integer downInDuct;

    @Column(name = "DOWN_MIDDLE_DUCT")
    private Integer downMiddleDuct;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DATA_SOURCE")
    private String dataSource;

    @Column(name = "LINE_UUID")
    private String lineUuid;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "CLASS_TYPE")
    private String classType;

    @Column(name = "ORDERCODE")
    private Short ordercode;

    @Column(name = "IS_TUNNEL")
    private String isTunnel;

    public String getIsTunnel() {
        return isTunnel;
    }

    public void setIsTunnel(String isTunnel) {
        this.isTunnel = isTunnel;
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
     * @return STATION_CODE
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * @param stationCode
     */
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
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
     * @return STATION_NAME
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * @param stationName
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
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
     * @return START_FLAG_TYPE
     */
    public String getStartFlagType() {
        return startFlagType;
    }

    /**
     * @param startFlagType
     */
    public void setStartFlagType(String startFlagType) {
        this.startFlagType = startFlagType;
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
     * @return END_FLAG_TYPE
     */
    public String getEndFlagType() {
        return endFlagType;
    }

    /**
     * @param endFlagType
     */
    public void setEndFlagType(String endFlagType) {
        this.endFlagType = endFlagType;
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
     * @return UP_OUT_MILEAGE
     */
    public BigDecimal getUpOutMileage() {
        return upOutMileage;
    }

    /**
     * @param upOutMileage
     */
    public void setUpOutMileage(BigDecimal upOutMileage) {
        this.upOutMileage = upOutMileage;
    }

    /**
     * @return UP_IN_MILEAGE
     */
    public BigDecimal getUpInMileage() {
        return upInMileage;
    }

    /**
     * @param upInMileage
     */
    public void setUpInMileage(BigDecimal upInMileage) {
        this.upInMileage = upInMileage;
    }

    /**
     * @return UP_MIDDLE_MILEAGE
     */
    public BigDecimal getUpMiddleMileage() {
        return upMiddleMileage;
    }

    /**
     * @param upMiddleMileage
     */
    public void setUpMiddleMileage(BigDecimal upMiddleMileage) {
        this.upMiddleMileage = upMiddleMileage;
    }

    /**
     * @return UP_OUT_DUCT
     */
    public Integer getUpOutDuct() {
        return upOutDuct;
    }

    /**
     * @param upOutDuct
     */
    public void setUpOutDuct(Integer upOutDuct) {
        this.upOutDuct = upOutDuct;
    }

    /**
     * @return UP_IN_DUCT
     */
    public Integer getUpInDuct() {
        return upInDuct;
    }

    /**
     * @param upInDuct
     */
    public void setUpInDuct(Integer upInDuct) {
        this.upInDuct = upInDuct;
    }

    /**
     * @return UP_MIDDLE_DUCT
     */
    public Integer getUpMiddleDuct() {
        return upMiddleDuct;
    }

    /**
     * @param upMiddleDuct
     */
    public void setUpMiddleDuct(Integer upMiddleDuct) {
        this.upMiddleDuct = upMiddleDuct;
    }

    /**
     * @return DOWN_OUT_MILEAGE
     */
    public BigDecimal getDownOutMileage() {
        return downOutMileage;
    }

    /**
     * @param downOutMileage
     */
    public void setDownOutMileage(BigDecimal downOutMileage) {
        this.downOutMileage = downOutMileage;
    }

    /**
     * @return DOWN_IN_MILEAGE
     */
    public BigDecimal getDownInMileage() {
        return downInMileage;
    }

    /**
     * @param downInMileage
     */
    public void setDownInMileage(BigDecimal downInMileage) {
        this.downInMileage = downInMileage;
    }

    /**
     * @return DOWN_MIDDLE_MILEAGE
     */
    public BigDecimal getDownMiddleMileage() {
        return downMiddleMileage;
    }

    /**
     * @param downMiddleMileage
     */
    public void setDownMiddleMileage(BigDecimal downMiddleMileage) {
        this.downMiddleMileage = downMiddleMileage;
    }

    /**
     * @return DOWN_OUT_DUCT
     */
    public Integer getDownOutDuct() {
        return downOutDuct;
    }

    /**
     * @param downOutDuct
     */
    public void setDownOutDuct(Integer downOutDuct) {
        this.downOutDuct = downOutDuct;
    }

    /**
     * @return DOWN_IN_DUCT
     */
    public Integer getDownInDuct() {
        return downInDuct;
    }

    /**
     * @param downInDuct
     */
    public void setDownInDuct(Integer downInDuct) {
        this.downInDuct = downInDuct;
    }

    /**
     * @return DOWN_MIDDLE_DUCT
     */
    public Integer getDownMiddleDuct() {
        return downMiddleDuct;
    }

    /**
     * @param downMiddleDuct
     */
    public void setDownMiddleDuct(Integer downMiddleDuct) {
        this.downMiddleDuct = downMiddleDuct;
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
     * @return DATA_SOURCE
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
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
     * @return CLASS_TYPE
     */
    public String getClassType() {
        return classType;
    }

    /**
     * @param classType
     */
    public void setClassType(String classType) {
        this.classType = classType;
    }

    /**
     * @return ORDERCODE
     */
    public Short getOrdercode() {
        return ordercode;
    }

    /**
     * @param ordercode
     */
    public void setOrdercode(Short ordercode) {
        this.ordercode = ordercode;
    }
}