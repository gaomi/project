package com.company.project.module.api.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "J302_API_AQBHQ_PERSON")
public class AqbhqPerson {
    @Id
    @Column(name = "PERSONID")
    private String personid;

    @Column(name = "PERSONNAME")
    private String personname;

    @Column(name = "PERSONSEX")
    private String personsex;

    @Column(name = "PERSONCONTACT")
    private String personcontact;

    @Column(name = "PERSONDESCRIPTION")
    private Object persondescription;

    @Column(name = "METROLINES")
    private Object metrolines;

    @Column(name = "INSERT_DATE")
    private Object insertDate;

    /**
     * @return PERSONID
     */
    public String getPersonid() {
        return personid;
    }

    /**
     * @param personid
     */
    public void setPersonid(String personid) {
        this.personid = personid;
    }

    /**
     * @return PERSONNAME
     */
    public String getPersonname() {
        return personname;
    }

    /**
     * @param personname
     */
    public void setPersonname(String personname) {
        this.personname = personname;
    }

    /**
     * @return PERSONSEX
     */
    public String getPersonsex() {
        return personsex;
    }

    /**
     * @param personsex
     */
    public void setPersonsex(String personsex) {
        this.personsex = personsex;
    }

    /**
     * @return PERSONCONTACT
     */
    public String getPersoncontact() {
        return personcontact;
    }

    /**
     * @param personcontact
     */
    public void setPersoncontact(String personcontact) {
        this.personcontact = personcontact;
    }

    /**
     * @return PERSONDESCRIPTION
     */
    public Object getPersondescription() {
        return persondescription;
    }

    /**
     * @param persondescription
     */
    public void setPersondescription(Object persondescription) {
        this.persondescription = persondescription;
    }

    /**
     * @return METROLINES
     */
    public Object getMetrolines() {
        return metrolines;
    }

    /**
     * @param metrolines
     */
    public void setMetrolines(Object metrolines) {
        this.metrolines = metrolines;
    }

    /**
     * @return INSERT_DATE
     */
    public Object getInsertDate() {
        return insertDate;
    }

    /**
     * @param insertDate
     */
    public void setInsertDate(Object insertDate) {
        this.insertDate = insertDate;
    }
}