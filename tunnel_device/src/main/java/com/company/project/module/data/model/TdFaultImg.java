package com.company.project.module.data.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "J302_TD_FAULT_IMG")
public class TdFaultImg {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "INTERNAL_CODE")
    private String internalCode;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

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
     * @return INTERNAL_CODE
     */
    public String getInternalCode() {
        return internalCode;
    }

    /**
     * @param internalCode
     */
    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    /**
     * @return IMAGE_URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}