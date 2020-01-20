package com.company.project.module.inspect.model;

import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

@ToString
@Table(name = "J302_EAM_WORKORDER")
public class EamWorkOrder {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "LINENO")
    private String lineno;

    @Column(name = "QMQX")
    private String qmqx;

    @Column(name = "PLANNATURE")
    private String plannature;

    @Column(name = "REGISTERSTA")
    private String registersta;

    @Column(name = "STARTSTA")
    private String startsta;

    @Column(name = "ENDSTA")
    private String endsta;

    @Column(name = "HIGHLOW")
    private String highlow;

    @Column(name = "WORKTYPE")
    private String worktype;

    @Column(name = "PLANSTARTTIME")
    private Date planstarttime;

    @Column(name = "PLANENDTIME")
    private Date planendtime;

    @Column(name = "DEPTID")
    private String deptid;

    @Column(name = "DUTYID")
    private String dutyid;

    @Column(name = "ISTD")
    private String istd;

    @Column(name = "TDSTART")
    private String tdstart;

    @Column(name = "TDEND")
    private String tdend;

    @Column(name = "PARTDESC")
    private String partdesc;

    @Column(name = "PLANIDEAM")
    private String planideam;

    @Column(name = "INSERT_DATE")
    private Date insertDate;
    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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
     * @return LINENO
     */
    public String getLineno() {
        return lineno;
    }

    /**
     * @param lineno
     */
    public void setLineno(String lineno) {
        this.lineno = lineno;
    }

    /**
     * @return QMQX
     */
    public String getQmqx() {
        return qmqx;
    }

    /**
     * @param qmqx
     */
    public void setQmqx(String qmqx) {
        this.qmqx = qmqx;
    }

    /**
     * @return PLANNATURE
     */
    public String getPlannature() {
        return plannature;
    }

    /**
     * @param plannature
     */
    public void setPlannature(String plannature) {
        this.plannature = plannature;
    }

    /**
     * @return REGISTERSTA
     */
    public String getRegistersta() {
        return registersta;
    }

    /**
     * @param registersta
     */
    public void setRegistersta(String registersta) {
        this.registersta = registersta;
    }

    /**
     * @return STARTSTA
     */
    public String getStartsta() {
        return startsta;
    }

    /**
     * @param startsta
     */
    public void setStartsta(String startsta) {
        this.startsta = startsta;
    }

    /**
     * @return ENDSTA
     */
    public String getEndsta() {
        return endsta;
    }

    /**
     * @param endsta
     */
    public void setEndsta(String endsta) {
        this.endsta = endsta;
    }

    /**
     * @return HIGHLOW
     */
    public String getHighlow() {
        return highlow;
    }

    /**
     * @param highlow
     */
    public void setHighlow(String highlow) {
        this.highlow = highlow;
    }

    /**
     * @return WORKTYPE
     */
    public String getWorktype() {
        return worktype;
    }

    /**
     * @param worktype
     */
    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    /**
     * @return PLANSTARTTIME
     */
    public Date getPlanstarttime() {
        return planstarttime;
    }

    /**
     * @param planstarttime
     */
    public void setPlanstarttime(Date planstarttime) {
        this.planstarttime = planstarttime;
    }

    /**
     * @return PLANENDTIME
     */
    public Date getPlanendtime() {
        return planendtime;
    }

    /**
     * @param planendtime
     */
    public void setPlanendtime(Date planendtime) {
        this.planendtime = planendtime;
    }

    /**
     * @return DEPTID
     */
    public String getDeptid() {
        return deptid;
    }

    /**
     * @param deptid
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    /**
     * @return DUTYID
     */
    public String getDutyid() {
        return dutyid;
    }

    /**
     * @param dutyid
     */
    public void setDutyid(String dutyid) {
        this.dutyid = dutyid;
    }

    /**
     * @return ISTD
     */
    public String getIstd() {
        return istd;
    }

    /**
     * @param istd
     */
    public void setIstd(String istd) {
        this.istd = istd;
    }

    /**
     * @return TDSTART
     */
    public String getTdstart() {
        return tdstart;
    }

    /**
     * @param tdstart
     */
    public void setTdstart(String tdstart) {
        this.tdstart = tdstart;
    }

    /**
     * @return TDEND
     */
    public String getTdend() {
        return tdend;
    }

    /**
     * @param tdend
     */
    public void setTdend(String tdend) {
        this.tdend = tdend;
    }

    /**
     * @return PARTDESC
     */
    public String getPartdesc() {
        return partdesc;
    }

    /**
     * @param partdesc
     */
    public void setPartdesc(String partdesc) {
        this.partdesc = partdesc;
    }

    /**
     * @return PLANIDEAM
     */
    public String getPlanideam() {
        return planideam;
    }

    /**
     * @param planideam
     */
    public void setPlanideam(String planideam) {
        this.planideam = planideam;
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
}