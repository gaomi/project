package com.company.project.module.app.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "J302_SI_APP_VERSION")
public class AppVersion {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "VERSION_NO")
    private String versionNo;

    @Column(name = "DOWNLOAD_URL")
    private String downloadUrl;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "APP_TYPE")
    private String appType;

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
     * @return VERSION_NO
     */
    public String getVersionNo() {
        return versionNo;
    }

    /**
     * @param versionNo
     */
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * @return DOWNLOAD_URL
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * @param downloadUrl
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return APP_TYPE
     */
    public String getAppType() {
        return appType;
    }

    /**
     * @param appType
     */
    public void setAppType(String appType) {
        this.appType = appType;
    }
}