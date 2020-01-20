package com.company.project.module.sys.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ToString
@Table(name = "J302_TD_SYS_USER")
public class SysUser implements Serializable {
    /**
     * 账户状态
     */
    public static final Long STATUS_VALID = 1L;

    public static final Long STATUS_LOCK = 0L;

    public static final String DEFAULT_THEME = "blue";

    public static final String DEFAULT_AVATAR = "default.jpg";

    /**
     * 性别
     */
    public static final String SEX_MALE = "0";

    public static final String SEX_FEMALE = "1";

    public static final String SEX_UNKNOW = "2";
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "TRUE_NAME")
    private String trueName;

    @Column(name = "DEPT_ID")
    private String deptId;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "SEX")
    private Long sex;

    @Column(name = "AVATAR")
    private String avatar;

    @Column(name = "PAGE_SIZE")
    private Long pageSize;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "OPERATOR")
    private String operator;

    @Column(name = "OPERATE_TIME")
    private Date operateTime;

    @Column(name = "OPERATE_IP")
    private String operateIp;

    @Column(name = "THEME")
    private String theme;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    @Column(name = "EMPLOYEE_NO")
    private String employeeNo;

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
     * @return USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return TELEPHONE
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return TRUE_NAME
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * @param trueName
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    /**
     * @return DEPT_ID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * @return MAIL
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return SEX
     */
    public Long getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Long sex) {
        this.sex = sex;
    }

    /**
     * @return AVATAR
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return PAGE_SIZE
     */
    public Long getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     */
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
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
     * @return LAST_LOGIN_TIME
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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

    /**
     * @return THEME
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }
}