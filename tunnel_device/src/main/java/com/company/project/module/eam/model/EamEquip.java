package com.company.project.module.eam.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "J302_EAM_EQUIP")
public class EamEquip {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "EQUIPNO")
    private String equipno;

    @Column(name = "EQUIPNAME")
    private String equipname;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "INSERT_DATE")
    private Date insertDate;

    @Column(name = "DUCT_CODE_START")
    private String ductCodeStart;

    @Column(name = "MILEAGE_CODE_START")
    private BigDecimal mileageCodeStart;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "DUCT_CODE_END")
    private String ductCodeEnd;

    @Column(name = "MILEAGE_CODE_END")
    private BigDecimal mileageCodeEnd;

    @Column(name = "MILEAGE_FLAG")
    private String mileageFlag;

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
     * @return EQUIPNO
     */
    public String getEquipno() {
        return equipno;
    }

    /**
     * @param equipno
     */
    public void setEquipno(String equipno) {
        this.equipno = equipno;
    }

    /**
     * @return EQUIPNAME
     */
    public String getEquipname() {
        return equipname;
    }

    /**
     * @param equipname
     */
    public void setEquipname(String equipname) {
        this.equipname = equipname;
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
     * @return INSERT_DATE
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * @param insertDate
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * @return DUCT_CODE_START
     */
    public String getDuctCodeStart() {
        return ductCodeStart;
    }

    /**
     * @param ductCodeStart
     */
    public void setDuctCodeStart(String ductCodeStart) {
        this.ductCodeStart = ductCodeStart;
    }

    /**
     * @return MILEAGE_CODE_START
     */
    public BigDecimal getMileageCodeStart() {
        return mileageCodeStart;
    }

    /**
     * @param mileageCodeStart
     */
    public void setMileageCodeStart(BigDecimal mileageCodeStart) {
        this.mileageCodeStart = mileageCodeStart;
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
     * @return DUCT_CODE_END
     */
    public String getDuctCodeEnd() {
        return ductCodeEnd;
    }

    /**
     * @param ductCodeEnd
     */
    public void setDuctCodeEnd(String ductCodeEnd) {
        this.ductCodeEnd = ductCodeEnd;
    }

    /**
     * @return MILEAGE_CODE_END
     */
    public BigDecimal getMileageCodeEnd() {
        return mileageCodeEnd;
    }

    /**
     * @param mileageCodeEnd
     */
    public void setMileageCodeEnd(BigDecimal mileageCodeEnd) {
        this.mileageCodeEnd = mileageCodeEnd;
    }

    /**
     * @return MILEAGE_FLAG
     */
    public String getMileageFlag() {
        return mileageFlag;
    }

    /**
     * @param mileageFlag
     */
    public void setMileageFlag(String mileageFlag) {
        this.mileageFlag = mileageFlag;
    }
}