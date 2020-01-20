package com.company.project.module.inspect.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@ToString
@Table(name = "J302_EAM_PERSON")
public class EamPerson {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "PERSONID")
    private String personId;

    @Column(name = "PERSONNAME")
    private String personName;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "INSERT_DATE")
    private Date insertDate;

    @Column(name = "INSPECT_PASSWORD")
    private String inspectPassword;

    public String getInspectPassword() {
        return inspectPassword;
    }

    public void setInspectPassword(String inspectPassword) {
        this.inspectPassword = inspectPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EamPerson that = (EamPerson) o;
        return personId.equals(that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
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
     * @return PERSONID
     */
    public String getPersonid() {
        return personId;
    }

    /**
     * @param personid
     */
    public void setPersonid(String personid) {
        this.personId = personid;
    }

    /**
     * @return PERSONNAME
     */
    public String getPersonname() {
        return personName;
    }

    /**
     * @param personname
     */
    public void setPersonname(String personname) {
        this.personName = personname;
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