package com.company.project.module.data.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@Table(name = "J302_TD_SEGMENT_INFO")
public class TdSegment {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "LINE_CODE")
    private String lineCode;

    @Column(name = "LINE_UUID")
    private String lineUuid;

    @Column(name = "SEGMENT_NAME")
    private String segmentName;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "START_STATION_CODE")
    private String startStationCode;

    @Column(name = "START_STATION_NAME")
    private String startStationName;

    @Column(name = "START_STATION_UUID")
    private String startStationUuid;

    @Column(name = "END_STATION_CODE")
    private String endStationCode;

    @Column(name = "END_STATION_NAME")
    private String endStationName;

    @Column(name = "END_STATION_UUID")
    private String endStationUuid;

    @Column(name = "SEGMENT_LONG")
    private String segmentLong;

    @Column(name = "TUNNEL_TYPE")
    private String tunnelType;

    @Column(name = "DUCT_COUNT")
    private Integer ductCount;

    @Column(name = "DUCT_SEQ")
    private String ductSeq;

    @Column(name = "START_DUCT_CODE")
    private Integer startDuctCode;

    @Column(name = "START_DUCT_UUID")
    private String startDuctUuid;

    @Column(name = "START_POINTS_NAME")
    private String startPointsName;

    @Column(name = "START_POINTS_UUID")
    private String startPointsUuid;

    @Column(name = "START_MILEAGE_CODE")
    private BigDecimal startMileageCode;

    @Column(name = "START_MILEAGE_FLAG")
    private String startMileageFlag;

    @Column(name = "END_DUCT_CODE")
    private Integer endDuctCode;

    @Column(name = "END_DUCT_UUID")
    private String endDuctUuid;

    @Column(name = "END_POINTS_NAME")
    private String endPointsName;

    @Column(name = "END_POINTS_UUID")
    private String endPointsUuid;

    @Column(name = "END_MILEAGE_CODE")
    private BigDecimal endMileageCode;

    @Column(name = "END_MILEAGE_FLAG")
    private String endMileageFlag;

    @Column(name = "RUN_DATE")
    private Date runDate;

    @Column(name = "DATA_SOURCE")
    private String dataSource;

    @Column(name = "NO_UD_UUID")
    private String noUdUuid;

    @Column(name = "SL_SEGMENT_UUID")
    private String slSegmentUuid;

    @Column(name = "SL_SEGMENT_NAME")
    private String slSegmentName;

    @Column(name = "CJ_SEGMENT_UUID")
    private String cjSegmentUuid;

    @Column(name = "CJ_SEGMENT_NAME")
    private String cjSegmentName;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "IS_TUNNEL")
    private String isTunnel;

    @Column(name = "SIGN_UUID_SL_A")
    private String signUuidSlA;

    @Column(name = "SIGN_UUID_SL_B")
    private String signUuidSlB;

    @Column(name = "NO_GROUP_UUID")
    private String noGroupUuid;

    @Column(name = "SIGN_UUID_SL_AA")
    private String signUuidSlAa;

    @Column(name = "SIGN_UUID_SL_BB")
    private String signUuidSlBb;

//    @Column(name = "UUID_LIST")
//    private List<String> uuidList;
//
//    public List getUuidList() {
//        return uuidList;
//    }
//
//    public void setUuidList(List uuidList) {
//        this.uuidList = uuidList;
//    }

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
     * @return SEGMENT_LONG
     */
    public String getSegmentLong() {
        return segmentLong;
    }

    /**
     * @param segmentLong
     */
    public void setSegmentLong(String segmentLong) {
        this.segmentLong = segmentLong;
    }

    /**
     * @return TUNNEL_TYPE
     */
    public String getTunnelType() {
        return tunnelType;
    }

    /**
     * @param tunnelType
     */
    public void setTunnelType(String tunnelType) {
        this.tunnelType = tunnelType;
    }

    /**
     * @return DUCT_COUNT
     */
    public Integer getDuctCount() {
        return ductCount;
    }

    /**
     * @param ductCount
     */
    public void setDuctCount(Integer ductCount) {
        this.ductCount = ductCount;
    }

    /**
     * @return DUCT_SEQ
     */
    public String getDuctSeq() {
        return ductSeq;
    }

    /**
     * @param ductSeq
     */
    public void setDuctSeq(String ductSeq) {
        this.ductSeq = ductSeq;
    }

    /**
     * @return START_DUCT_CODE
     */
    public Integer getStartDuctCode() {
        return startDuctCode;
    }

    /**
     * @param startDuctCode
     */
    public void setStartDuctCode(Integer startDuctCode) {
        this.startDuctCode = startDuctCode;
    }

    /**
     * @return START_DUCT_UUID
     */
    public String getStartDuctUuid() {
        return startDuctUuid;
    }

    /**
     * @param startDuctUuid
     */
    public void setStartDuctUuid(String startDuctUuid) {
        this.startDuctUuid = startDuctUuid;
    }

    /**
     * @return START_POINTS_NAME
     */
    public String getStartPointsName() {
        return startPointsName;
    }

    /**
     * @param startPointsName
     */
    public void setStartPointsName(String startPointsName) {
        this.startPointsName = startPointsName;
    }

    /**
     * @return START_POINTS_UUID
     */
    public String getStartPointsUuid() {
        return startPointsUuid;
    }

    /**
     * @param startPointsUuid
     */
    public void setStartPointsUuid(String startPointsUuid) {
        this.startPointsUuid = startPointsUuid;
    }

    /**
     * @return START_MILEAGE_CODE
     */
    public BigDecimal getStartMileageCode() {
        return startMileageCode;
    }

    /**
     * @param startMileageCode
     */
    public void setStartMileageCode(BigDecimal startMileageCode) {
        this.startMileageCode = startMileageCode;
    }

    /**
     * @return START_MILEAGE_FLAG
     */
    public String getStartMileageFlag() {
        return startMileageFlag;
    }

    /**
     * @param startMileageFlag
     */
    public void setStartMileageFlag(String startMileageFlag) {
        this.startMileageFlag = startMileageFlag;
    }

    /**
     * @return END_DUCT_CODE
     */
    public Integer getEndDuctCode() {
        return endDuctCode;
    }

    /**
     * @param endDuctCode
     */
    public void setEndDuctCode(Integer endDuctCode) {
        this.endDuctCode = endDuctCode;
    }

    /**
     * @return END_DUCT_UUID
     */
    public String getEndDuctUuid() {
        return endDuctUuid;
    }

    /**
     * @param endDuctUuid
     */
    public void setEndDuctUuid(String endDuctUuid) {
        this.endDuctUuid = endDuctUuid;
    }

    /**
     * @return END_POINTS_NAME
     */
    public String getEndPointsName() {
        return endPointsName;
    }

    /**
     * @param endPointsName
     */
    public void setEndPointsName(String endPointsName) {
        this.endPointsName = endPointsName;
    }

    /**
     * @return END_POINTS_UUID
     */
    public String getEndPointsUuid() {
        return endPointsUuid;
    }

    /**
     * @param endPointsUuid
     */
    public void setEndPointsUuid(String endPointsUuid) {
        this.endPointsUuid = endPointsUuid;
    }

    /**
     * @return END_MILEAGE_CODE
     */
    public BigDecimal getEndMileageCode() {
        return endMileageCode;
    }

    /**
     * @param endMileageCode
     */
    public void setEndMileageCode(BigDecimal endMileageCode) {
        this.endMileageCode = endMileageCode;
    }

    /**
     * @return END_MILEAGE_FLAG
     */
    public String getEndMileageFlag() {
        return endMileageFlag;
    }

    /**
     * @param endMileageFlag
     */
    public void setEndMileageFlag(String endMileageFlag) {
        this.endMileageFlag = endMileageFlag;
    }

    /**
     * @return RUN_DATE
     */
    public Date getRunDate() {
        return runDate;
    }

    /**
     * @param runDate
     */
    public void setRunDate(Date runDate) {
        this.runDate = runDate;
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
     * @return NO_UD_UUID
     */
    public String getNoUdUuid() {
        return noUdUuid;
    }

    /**
     * @param noUdUuid
     */
    public void setNoUdUuid(String noUdUuid) {
        this.noUdUuid = noUdUuid;
    }

    /**
     * @return SL_SEGMENT_UUID
     */
    public String getSlSegmentUuid() {
        return slSegmentUuid;
    }

    /**
     * @param slSegmentUuid
     */
    public void setSlSegmentUuid(String slSegmentUuid) {
        this.slSegmentUuid = slSegmentUuid;
    }

    /**
     * @return SL_SEGMENT_NAME
     */
    public String getSlSegmentName() {
        return slSegmentName;
    }

    /**
     * @param slSegmentName
     */
    public void setSlSegmentName(String slSegmentName) {
        this.slSegmentName = slSegmentName;
    }

    /**
     * @return CJ_SEGMENT_UUID
     */
    public String getCjSegmentUuid() {
        return cjSegmentUuid;
    }

    /**
     * @param cjSegmentUuid
     */
    public void setCjSegmentUuid(String cjSegmentUuid) {
        this.cjSegmentUuid = cjSegmentUuid;
    }

    /**
     * @return CJ_SEGMENT_NAME
     */
    public String getCjSegmentName() {
        return cjSegmentName;
    }

    /**
     * @param cjSegmentName
     */
    public void setCjSegmentName(String cjSegmentName) {
        this.cjSegmentName = cjSegmentName;
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
     * @return IS_TUNNEL
     */
    public String getIsTunnel() {
        return isTunnel;
    }

    /**
     * @param isTunnel
     */
    public void setIsTunnel(String isTunnel) {
        this.isTunnel = isTunnel;
    }

    /**
     * @return SIGN_UUID_SL_A
     */
    public String getSignUuidSlA() {
        return signUuidSlA;
    }

    /**
     * @param signUuidSlA
     */
    public void setSignUuidSlA(String signUuidSlA) {
        this.signUuidSlA = signUuidSlA;
    }

    /**
     * @return SIGN_UUID_SL_B
     */
    public String getSignUuidSlB() {
        return signUuidSlB;
    }

    /**
     * @param signUuidSlB
     */
    public void setSignUuidSlB(String signUuidSlB) {
        this.signUuidSlB = signUuidSlB;
    }

    /**
     * @return NO_GROUP_UUID
     */
    public String getNoGroupUuid() {
        return noGroupUuid;
    }

    /**
     * @param noGroupUuid
     */
    public void setNoGroupUuid(String noGroupUuid) {
        this.noGroupUuid = noGroupUuid;
    }

    /**
     * @return SIGN_UUID_SL_AA
     */
    public String getSignUuidSlAa() {
        return signUuidSlAa;
    }

    /**
     * @param signUuidSlAa
     */
    public void setSignUuidSlAa(String signUuidSlAa) {
        this.signUuidSlAa = signUuidSlAa;
    }

    /**
     * @return SIGN_UUID_SL_BB
     */
    public String getSignUuidSlBb() {
        return signUuidSlBb;
    }

    /**
     * @param signUuidSlBb
     */
    public void setSignUuidSlBb(String signUuidSlBb) {
        this.signUuidSlBb = signUuidSlBb;
    }
}