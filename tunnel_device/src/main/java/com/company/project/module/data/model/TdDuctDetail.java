package com.company.project.module.data.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "J302_TD_DUCT_DETAIL")
public class TdDuctDetail {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "SEGMENT_UUID")
    private String segmentUuid;

    @Column(name = "DUCT")
    private Long duct;

    @Column(name = "JH_COUNT")
    private Long jhCount;

    @Column(name = "BJ_COUNT")
    private Long bjCount;

    @Column(name = "WG_COUNT")
    private Long wgCount;

    @Column(name = "TC_COUNT")
    private Long tcCount;

    @Column(name = "WX_COUNT")
    private Long wxCount;

    @Column(name = "BH_COUNT")
    private Long bhCount;

    @Column(name = "BH_LEVEL")
    private String bhLevel;

    @Column(name = "LINE_UUID")
    private String lineUuid;

    /**
     * @return UUID
     */
    public String getLineUuid() {
        return lineUuid;
    }

    /**
     * @param uuid
     */
    public void setLineUuid(String uuid) {
        this.lineUuid = lineUuid;
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
     * @return DUCT
     */
    public Long getDuct() {
        return duct;
    }

    /**
     * @param duct
     */
    public void setDuct(Long duct) {
        this.duct = duct;
    }

    /**
     * @return JH_COUNT
     */
    public Long getJhCount() {
        return jhCount;
    }

    /**
     * @param jhCount
     */
    public void setJhCount(Long jhCount) {
        this.jhCount = jhCount;
    }

    /**
     * @return BJ_COUNT
     */
    public Long getBjCount() {
        return bjCount;
    }

    /**
     * @param bjCount
     */
    public void setBjCount(Long bjCount) {
        this.bjCount = bjCount;
    }

    /**
     * @return WG_COUNT
     */
    public Long getWgCount() {
        return wgCount;
    }

    /**
     * @param wgCount
     */
    public void setWgCount(Long wgCount) {
        this.wgCount = wgCount;
    }

    /**
     * @return TC_COUNT
     */
    public Long getTcCount() {
        return tcCount;
    }

    /**
     * @param tcCount
     */
    public void setTcCount(Long tcCount) {
        this.tcCount = tcCount;
    }

    /**
     * @return WX_COUNT
     */
    public Long getWxCount() {
        return wxCount;
    }

    /**
     * @param wxCount
     */
    public void setWxCount(Long wxCount) {
        this.wxCount = wxCount;
    }

    /**
     * @return BH_COUNT
     */
    public Long getBhCount() {
        return bhCount;
    }

    /**
     * @param bhCount
     */
    public void setBhCount(Long bhCount) {
        this.bhCount = bhCount;
    }

    /**
     * @return BH_LEVEL
     */
    public String getBhLevel() {
        return bhLevel;
    }

    /**
     * @param bhLevel
     */
    public void setBhLevel(String bhLevel) {
        this.bhLevel = bhLevel;
    }
}