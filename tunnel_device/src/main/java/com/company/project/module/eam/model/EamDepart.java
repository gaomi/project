package com.company.project.module.eam.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "J302_EAM_DEPART")
public class EamDepart {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "DEPTID")
    private String deptid;

    @Column(name = "DEPTNAME")
    private String deptname;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "INSERT_DATE")
    private Date insertDate;

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
     * @return DEPTNAME
     */
    public String getDeptname() {
        return deptname;
    }

    /**
     * @param deptname
     */
    public void setDeptname(String deptname) {
        this.deptname = deptname;
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
}