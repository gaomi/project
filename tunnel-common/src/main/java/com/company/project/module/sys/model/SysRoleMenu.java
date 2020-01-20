package com.company.project.module.sys.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@ToString
@Table(name = "J302_TD_SYS_ROLE_MENU")
public class SysRoleMenu {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "MENU_ID")
    private String menuId;

    @Column(name = "ROLE_ID")
    private String roleId;

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
     * @return MENU_ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * @return ROLE_ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
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