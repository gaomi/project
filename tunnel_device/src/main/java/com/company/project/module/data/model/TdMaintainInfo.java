package com.company.project.module.data.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "J302_TD_MAINTAIN_INFO")
public class TdMaintainInfo {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "LINE_NAME")
    private String lineName;

    @Column(name = "SEGMENT_RANGE")
    private String segmentRange;

    @Column(name = "FAULT_DESC")
    private String faultDesc;

    @Column(name = "DIRECTION")
    private String direction;

    @Column(name = "DUCT_LOC")
    private String ductLoc;

    @Column(name = "DISE_DESC")
    private String diseDesc;

    @Column(name = "DEAL_TYPE")
    private String dealType;

    @Column(name = "CLASSIFY")
    private String classify;

    @Column(name = "TUNNEL")
    private String tunnel;

    @Column(name = "BOSS_NAME")
    private String bossName;

    @Column(name = "FOLLOW_WORK")
    private String followWork;

    @Column(name = "MATERIAL_CONSUMPTION")
    private String materialConsumption;

    @Column(name = "AFTER_LEVEL")
    private Short afterLevel;

    @Column(name = "CHA_LEVEL")
    private Short chaLevel;

    @Column(name = "WORK_ORDER_ID")
    private String workOrderId;

    @Column(name = "DATA_TIME")
    private String dataTime;

    @Column(name = "BEFOR_LEVEL")
    private Short beforLevel;

    @Column(name = "PEOPLE_COUNT")
    private String peopleCount;

    @Column(name = "DEAL_CONDITION")
    private String dealCondition;

    @Column(name = "PARTICIPANT")
    private String participant;

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
     * @return SEGMENT_RANGE
     */
    public String getSegmentRange() {
        return segmentRange;
    }

    /**
     * @param segmentRange
     */
    public void setSegmentRange(String segmentRange) {
        this.segmentRange = segmentRange;
    }

    /**
     * @return FAULT_DESC
     */
    public String getFaultDesc() {
        return faultDesc;
    }

    /**
     * @param faultDesc
     */
    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc;
    }

    /**
     * @return DIRECTION
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return DUCT_LOC
     */
    public String getDuctLoc() {
        return ductLoc;
    }

    /**
     * @param ductLoc
     */
    public void setDuctLoc(String ductLoc) {
        this.ductLoc = ductLoc;
    }

    /**
     * @return DISE_DESC
     */
    public String getDiseDesc() {
        return diseDesc;
    }

    /**
     * @param diseDesc
     */
    public void setDiseDesc(String diseDesc) {
        this.diseDesc = diseDesc;
    }

    /**
     * @return DEAL_TYPE
     */
    public String getDealType() {
        return dealType;
    }

    /**
     * @param dealType
     */
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    /**
     * @return CLASSIFY
     */
    public String getClassify() {
        return classify;
    }

    /**
     * @param classify
     */
    public void setClassify(String classify) {
        this.classify = classify;
    }

    /**
     * @return TUNNEL
     */
    public String getTunnel() {
        return tunnel;
    }

    /**
     * @param tunnel
     */
    public void setTunnel(String tunnel) {
        this.tunnel = tunnel;
    }

    /**
     * @return BOSS_NAME
     */
    public String getBossName() {
        return bossName;
    }

    /**
     * @param bossName
     */
    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    /**
     * @return FOLLOW_WORK
     */
    public String getFollowWork() {
        return followWork;
    }

    /**
     * @param followWork
     */
    public void setFollowWork(String followWork) {
        this.followWork = followWork;
    }

    /**
     * @return MATERIAL_CONSUMPTION
     */
    public String getMaterialConsumption() {
        return materialConsumption;
    }

    /**
     * @param materialConsumption
     */
    public void setMaterialConsumption(String materialConsumption) {
        this.materialConsumption = materialConsumption;
    }

    /**
     * @return AFTER_LEVEL
     */
    public Short getAfterLevel() {
        return afterLevel;
    }

    /**
     * @param afterLevel
     */
    public void setAfterLevel(Short afterLevel) {
        this.afterLevel = afterLevel;
    }

    /**
     * @return CHA_LEVEL
     */
    public Short getChaLevel() {
        return chaLevel;
    }

    /**
     * @param chaLevel
     */
    public void setChaLevel(Short chaLevel) {
        this.chaLevel = chaLevel;
    }

    /**
     * @return WORK_ORDER_ID
     */
    public String getWorkOrderId() {
        return workOrderId;
    }

    /**
     * @param workOrderId
     */
    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    /**
     * @return DATA_TIME
     */
    public String getDataTime() {
        return dataTime;
    }

    /**
     * @param dataTime
     */
    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * @return BEFOR_LEVEL
     */
    public Short getBeforLevel() {
        return beforLevel;
    }

    /**
     * @param beforLevel
     */
    public void setBeforLevel(Short beforLevel) {
        this.beforLevel = beforLevel;
    }

    /**
     * @return PEOPLE_COUNT
     */
    public String getPeopleCount() {
        return peopleCount;
    }

    /**
     * @param peopleCount
     */
    public void setPeopleCount(String peopleCount) {
        this.peopleCount = peopleCount;
    }

    /**
     * @return DEAL_CONDITION
     */
    public String getDealCondition() {
        return dealCondition;
    }

    /**
     * @param dealCondition
     */
    public void setDealCondition(String dealCondition) {
        this.dealCondition = dealCondition;
    }

    /**
     * @return PARTICIPANT
     */
    public String getParticipant() {
        return participant;
    }

    /**
     * @param participant
     */
    public void setParticipant(String participant) {
        this.participant = participant;
    }
}