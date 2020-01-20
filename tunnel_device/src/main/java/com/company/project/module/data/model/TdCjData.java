package com.company.project.module.data.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "J302_METRO_CJ_INFO")
public class TdCjData {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "DUCT")
    private String duct;

    @Column(name = "METER")
    private BigDecimal meter;

    @Column(name = "CJ_YEAR")
    private String cjYear;

    @Column(name = "CJ_VALUE")
    private BigDecimal cjValue;

    @Column(name = "POINT_CODE")
    private String pointCode;

    @Column(name = "SEGMENT_ID")
    private String segmentId;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return DUCT
     */
    public String getDuct() {
        return duct;
    }

    /**
     * @param duct
     */
    public void setDuct(String duct) {
        this.duct = duct;
    }

    /**
     * @return METER
     */
    public BigDecimal getMeter() {
        return meter;
    }

    /**
     * @param meter
     */
    public void setMeter(BigDecimal meter) {
        this.meter = meter;
    }

    /**
     * @return CJ_YEAR
     */
    public String getCjYear() {
        return cjYear;
    }

    /**
     * @param cjYear
     */
    public void setCjYear(String cjYear) {
        this.cjYear = cjYear;
    }

    /**
     * @return CJ_VALUE
     */
    public BigDecimal getCjValue() {
        return cjValue;
    }

    /**
     * @param cjValue
     */
    public void setCjValue(BigDecimal cjValue) {
        this.cjValue = cjValue;
    }

    /**
     * @return POINT
     */
    public String getPoint() {
        return pointCode;
    }

    /**
     * @param point
     */
    public void setPoint(String point) {
        this.pointCode = point;
    }

    /**
     * @return SEGMENT_ID
     */
    public String getSegmentId() {
        return segmentId;
    }

    /**
     * @param segmentId
     */
    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }
}