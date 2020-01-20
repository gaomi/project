package com.company.project.module.inspect.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "J302_SI_FAULT_PLAN")
public class FaultPlan {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "PLAN_NO")
    private String planNo;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PLAN_TYPE")
    private String planType;

    @Column(name = "LINE_UUID")
    private String lineUuid;

    @Column(name = "WORK_TYPE")
    private String workType;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "MAJOR")
    private String major;

    @Column(name = "START_STATION_UUID")
    private String startStationUuid;

    @Column(name = "END_STATION_UUID")
    private String endStationUuid;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "END_TIME")
    private Date endTime;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "LEADER_UUID")
    private String leaderUuid;

    @Column(name = "EXECUTIVES")
    private String executives;

    @Column(name = "INFLUENCE")
    private String influence;

    @Column(name = "PREVENTION")
    private String prevention;

    @Column(name = "DETAIL_DESC")
    private String detailDesc;

    @Column(name = "STATION_TYPE")
    private String stationType;

    @Column(name = "ENTER_STATION_UUID")
    private String enterStationUuid;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "LEADER_TEL")
    private String leaderTel;

    @Column(name = "IS_OUTAGE")
    private String isOutage;

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
     * @return PLAN_NO
     */
    public String getPlanNo() {
        return planNo;
    }

    /**
     * @param planNo
     */
    public void setPlanNo(String planNo) {
        this.planNo = planNo;
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
     * @return PLAN_TYPE
     */
    public String getPlanType() {
        return planType;
    }

    /**
     * @param planType
     */
    public void setPlanType(String planType) {
        this.planType = planType;
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
     * @return WORK_TYPE
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * @param workType
     */
    public void setWorkType(String workType) {
        this.workType = workType;
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
     * @return MAJOR
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
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
     * @return START_TIME
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return END_TIME
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return LEADER_UUID
     */
    public String getLeaderUuid() {
        return leaderUuid;
    }

    /**
     * @param leaderUuid
     */
    public void setLeaderUuid(String leaderUuid) {
        this.leaderUuid = leaderUuid;
    }

    /**
     * @return EXECUTIVES
     */
    public String getExecutives() {
        return executives;
    }

    /**
     * @param executives
     */
    public void setExecutives(String executives) {
        this.executives = executives;
    }

    /**
     * @return INFLUENCE
     */
    public String getInfluence() {
        return influence;
    }

    /**
     * @param influence
     */
    public void setInfluence(String influence) {
        this.influence = influence;
    }

    /**
     * @return PREVENTION
     */
    public String getPrevention() {
        return prevention;
    }

    /**
     * @param prevention
     */
    public void setPrevention(String prevention) {
        this.prevention = prevention;
    }

    /**
     * @return DETAIL_DESC
     */
    public String getDetailDesc() {
        return detailDesc;
    }

    /**
     * @param detailDesc
     */
    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    /**
     * @return STATION_TYPE
     */
    public String getStationType() {
        return stationType;
    }

    /**
     * @param stationType
     */
    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    /**
     * @return ENTER_STATION_UUID
     */
    public String getEnterStationUuid() {
        return enterStationUuid;
    }

    /**
     * @param enterStationUuid
     */
    public void setEnterStationUuid(String enterStationUuid) {
        this.enterStationUuid = enterStationUuid;
    }

    /**
     * @return COMPANY_NAME
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return LEADER_TEL
     */
    public String getLeaderTel() {
        return leaderTel;
    }

    /**
     * @param leaderTel
     */
    public void setLeaderTel(String leaderTel) {
        this.leaderTel = leaderTel;
    }

    /**
     * @return IS_OUTAGE
     */
    public String getIsOutage() {
        return isOutage;
    }

    /**
     * @param isOutage
     */
    public void setIsOutage(String isOutage) {
        this.isOutage = isOutage;
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