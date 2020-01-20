package com.company.project.module.sys.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@ToString
@Table(name = "J302_TD_SYS_MENU")
public class SysMenu {
    public static final Integer TYPE_MENU = 0;

    public static final Integer TYPE_BUTTON = 1;
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "MENU_TYPE")
    private Long menuType;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATE")
    private Long state;

    @Column(name = "URL")
    private String url;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "TYPE")
    private Long type;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "PERMISSION")
    private String permission;

    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "MENU_LEVEL")
    private String menuLevel;

    @Column(name = "OPERATOR")
    private String operator;

    @Column(name = "OPERATE_TIME")
    private Date operateTime;

    @Column(name = "OPERATE_IP")
    private String operateIp;

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
     * @return MENU_TYPE
     */
    public Long getMenuType() {
        return menuType;
    }

    /**
     * @param menuType
     */
    public void setMenuType(Long menuType) {
        this.menuType = menuType;
    }

    /**
     * @return ICON
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
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
     * @return STATE
     */
    public Long getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Long state) {
        this.state = state;
    }

    /**
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return PARENT_ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return TYPE
     */
    public Long getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Long type) {
        this.type = type;
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
     * @return STATUS
     */
    public Long getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * @return PERMISSION
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return SEQ
     */
    public Long getSeq() {
        return seq;
    }

    /**
     * @param seq
     */
    public void setSeq(Long seq) {
        this.seq = seq;
    }

    /**
     * @return MENU_LEVEL
     */
    public String getMenuLevel() {
        return menuLevel;
    }

    /**
     * @param menuLevel
     */
    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    /**
     * @return OPERATOR
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return OPERATE_TIME
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * @param operateTime
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * @return OPERATE_IP
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * @param operateIp
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}