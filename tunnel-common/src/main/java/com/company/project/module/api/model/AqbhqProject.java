package com.company.project.module.api.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "J302_API_AQBHQ_PROJECT")
public class AqbhqProject {
    @Id
    @Column(name = "PROJECTID")
    private String projectid;

    @Column(name = "PROJECTNAME")
    private String projectname;

    @Column(name = "METROLINENAME")
    private String metrolinename;

    @Column(name = "METROSTATIONNAMESTART")
    private String metrostationnamestart;

    @Column(name = "METROSTATIONNAMEEND")
    private String metrostationnameend;

    @Column(name = "PROJECTTYPE")
    private Long projecttype;

    @Column(name = "PROJECTLEVEL")
    private String projectlevel;

    @Column(name = "PROJTASKLASTRESULTSTATUS")
    private String projtasklastresultstatus;

    @Column(name = "WHETHERSUBSEQUENTPROCESSING")
    private String whethersubsequentprocessing;

    @Column(name = "WHETHERKEYPROJECT")
    private String whetherkeyproject;

    @Column(name = "PROJECTBUILDUNIT")
    private String projectbuildunit;

    @Column(name = "PROJECTBUILDUNITCONTACT")
    private String projectbuildunitcontact;

    @Column(name = "PROJECTCONSTRUCTIONUNIT")
    private String projectconstructionunit;

    @Column(name = "PROJECTCONSTRUCTIONUNITCONTACT")
    private String projectconstructionunitcontact;

    @Column(name = "PROJECTDESCRIPTION")
    private String projectdescription;

    @Column(name = "PROJECTADDRESS")
    private String projectaddress;

    @Column(name = "PROJECTAREA")
    private String projectarea;

    @Column(name = "PROJECTLOCATIONWITHMETRO")
    private String projectlocationwithmetro;

    @Column(name = "LAT")
    private BigDecimal lat;

    @Column(name = "LNG")
    private BigDecimal lng;

    @Column(name = "CLNG")
    private BigDecimal clng;

    @Column(name = "CLAT")
    private BigDecimal clat;

    @Column(name = "NO_UD_UUID")
    private String noUdUuid;

    @Column(name = "U_START_DUCT")
    private Object uStartDuct;

    @Column(name = "U_END_DUCT")
    private Object uEndDuct;

    @Column(name = "D_START_DUCT")
    private Object dStartDuct;

    @Column(name = "D_END_DUCT")
    private Object dEndDuct;

    @Column(name = "INSERT_TIME")
    private Object insertTime;

    @Column(name = "SEGMENT_UUID")
    private Object segmentUuid;

    public Object getSegmentUuid() {
        return segmentUuid;
    }

    public void setSegmentUuid(Object segmentUuid) {
        this.segmentUuid = segmentUuid;
    }

    public Object getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Object insertTime) {
        this.insertTime = insertTime;
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


    public String getNoUdUuid() {
        return noUdUuid;
    }

    public void setNoUdUuid(String noUdUuid) {
        this.noUdUuid = noUdUuid;
    }

    /**
     * @return PROJECTID
     */
    public String getProjectid() {
        return projectid;
    }

    /**
     * @param projectid
     */
    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    /**
     * @return PROJECTNAME
     */
    public String getProjectname() {
        return projectname;
    }

    /**
     * @param projectname
     */
    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    /**
     * @return METROLINENAME
     */
    public String getMetrolinename() {
        return metrolinename;
    }

    /**
     * @param metrolinename
     */
    public void setMetrolinename(String metrolinename) {
        this.metrolinename = metrolinename;
    }

    /**
     * @return METROSTATIONNAMESTART
     */
    public String getMetrostationnamestart() {
        return metrostationnamestart;
    }

    /**
     * @param metrostationnamestart
     */
    public void setMetrostationnamestart(String metrostationnamestart) {
        this.metrostationnamestart = metrostationnamestart;
    }

    /**
     * @return METROSTATIONNAMEEND
     */
    public String getMetrostationnameend() {
        return metrostationnameend;
    }

    /**
     * @param metrostationnameend
     */
    public void setMetrostationnameend(String metrostationnameend) {
        this.metrostationnameend = metrostationnameend;
    }

    /**
     * @return PROJECTTYPE
     */
    public Long getProjecttype() {
        return projecttype;
    }

    /**
     * @param projecttype
     */
    public void setProjecttype(Long projecttype) {
        this.projecttype = projecttype;
    }

    /**
     * @return PROJECTLEVEL
     */
    public String getProjectlevel() {
        return projectlevel;
    }

    /**
     * @param projectlevel
     */
    public void setProjectlevel(String projectlevel) {
        this.projectlevel = projectlevel;
    }

    /**
     * @return PROJTASKLASTRESULTSTATUS
     */
    public String getProjtasklastresultstatus() {
        return projtasklastresultstatus;
    }

    /**
     * @param projtasklastresultstatus
     */
    public void setProjtasklastresultstatus(String projtasklastresultstatus) {
        this.projtasklastresultstatus = projtasklastresultstatus;
    }

    /**
     * @return WHETHERSUBSEQUENTPROCESSING
     */
    public String getWhethersubsequentprocessing() {
        return whethersubsequentprocessing;
    }

    /**
     * @param whethersubsequentprocessing
     */
    public void setWhethersubsequentprocessing(String whethersubsequentprocessing) {
        this.whethersubsequentprocessing = whethersubsequentprocessing;
    }

    /**
     * @return WHETHERKEYPROJECT
     */
    public String getWhetherkeyproject() {
        return whetherkeyproject;
    }

    /**
     * @param whetherkeyproject
     */
    public void setWhetherkeyproject(String whetherkeyproject) {
        this.whetherkeyproject = whetherkeyproject;
    }

    /**
     * @return PROJECTBUILDUNIT
     */
    public String getProjectbuildunit() {
        return projectbuildunit;
    }

    /**
     * @param projectbuildunit
     */
    public void setProjectbuildunit(String projectbuildunit) {
        this.projectbuildunit = projectbuildunit;
    }

    /**
     * @return PROJECTBUILDUNITCONTACT
     */
    public String getProjectbuildunitcontact() {
        return projectbuildunitcontact;
    }

    /**
     * @param projectbuildunitcontact
     */
    public void setProjectbuildunitcontact(String projectbuildunitcontact) {
        this.projectbuildunitcontact = projectbuildunitcontact;
    }

    /**
     * @return PROJECTCONSTRUCTIONUNIT
     */
    public String getProjectconstructionunit() {
        return projectconstructionunit;
    }

    /**
     * @param projectconstructionunit
     */
    public void setProjectconstructionunit(String projectconstructionunit) {
        this.projectconstructionunit = projectconstructionunit;
    }

    /**
     * @return PROJECTCONSTRUCTIONUNITCONTACT
     */
    public String getProjectconstructionunitcontact() {
        return projectconstructionunitcontact;
    }

    /**
     * @param projectconstructionunitcontact
     */
    public void setProjectconstructionunitcontact(String projectconstructionunitcontact) {
        this.projectconstructionunitcontact = projectconstructionunitcontact;
    }

    /**
     * @return PROJECTDESCRIPTION
     */
    public String getProjectdescription() {
        return projectdescription;
    }

    /**
     * @param projectdescription
     */
    public void setProjectdescription(String projectdescription) {
        this.projectdescription = projectdescription;
    }

    /**
     * @return PROJECTADDRESS
     */
    public String getProjectaddress() {
        return projectaddress;
    }

    /**
     * @param projectaddress
     */
    public void setProjectaddress(String projectaddress) {
        this.projectaddress = projectaddress;
    }

    /**
     * @return PROJECTAREA
     */
    public String getProjectarea() {
        return projectarea;
    }

    /**
     * @param projectarea
     */
    public void setProjectarea(String projectarea) {
        this.projectarea = projectarea;
    }

    /**
     * @return PROJECTLOCATIONWITHMETRO
     */
    public String getProjectlocationwithmetro() {
        return projectlocationwithmetro;
    }

    /**
     * @param projectlocationwithmetro
     */
    public void setProjectlocationwithmetro(String projectlocationwithmetro) {
        this.projectlocationwithmetro = projectlocationwithmetro;
    }

    /**
     * @return LAT
     */
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * @param lat
     */
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    /**
     * @return LNG
     */
    public BigDecimal getLng() {
        return lng;
    }

    /**
     * @param lng
     */
    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    /**
     * @return CLNG
     */
    public BigDecimal getClng() {
        return clng;
    }

    /**
     * @param clng
     */
    public void setClng(BigDecimal clng) {
        this.clng = clng;
    }

    /**
     * @return CLAT
     */
    public BigDecimal getClat() {
        return clat;
    }

    /**
     * @param clat
     */
    public void setClat(BigDecimal clat) {
        this.clat = clat;
    }
}