package com.company.project.module.api.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@ToString
@RequiredArgsConstructor
@Table(name = "J302_API_JHJC_PROJECT")
public class JhjcProject {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "LINE")
    private String line;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CENTERJSON")
    private String centerjson;

    @Column(name = "LEVEL")
    private String level;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COMPANY")
    private String company;


    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "U_START_DUCT")
    private Object uStartDuct;

    @Column(name = "U_END_DUCT")
    private Object uEndDuct;

    @Column(name = "D_START_DUCT")
    private Object dStartDuct;

    @Column(name = "D_END_DUCT")
    private Object dEndDuct;

    @Column(name = "SEGMENT_UUID")
    private Object segmentUuid;

    public Object getSegmentName() {
        return segmentUuid;
    }

    public void setSegmentName(Object segmentName) {
        this.segmentUuid = segmentName;
    }

    public Object getuStartDuct() {
        return uStartDuct;
    }

    public void setuStartDuct(Object uStartDuct) {
        this.uStartDuct = uStartDuct;
    }

    public Object getuEndDuct() {
        return uEndDuct;
    }

    public void setuEndDuct(Object uEndDuct) {
        this.uEndDuct = uEndDuct;
    }

    public Object getdStartDuct() {
        return dStartDuct;
    }

    public void setdStartDuct(Object dStartDuct) {
        this.dStartDuct = dStartDuct;
    }

    public Object getdEndDuct() {
        return dEndDuct;
    }

    public void setdEndDuct(Object dEndDuct) {
        this.dEndDuct = dEndDuct;
    }

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
     * @return LINE
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return CENTERJSON
     */
    public String getCenterjson() {
        return centerjson;
    }

    /**
     * @param centerjson
     */
    public void setCenterjson(String centerjson) {
        this.centerjson = centerjson;
    }

    /**
     * @return LEVEL
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return STATE
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return COMPANY
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }


    /**
     * @return SUMMARY
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}