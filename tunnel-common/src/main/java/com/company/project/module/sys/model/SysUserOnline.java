package com.company.project.module.sys.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 在线用户
 *
 * @author Chen
 * @created 2019-05-21-22:21.
 */
@Getter
@Setter
@ToString
public class SysUserOnline {
    // session id
    private String id;
    // 用户id
    private String userId;
    // 用户名称
    private String username;
    // 用户主机地址
    private String host;
    // 用户登录时系统IP
    private String systemHost;
    // 状态
    private String status;
    // session创建时间
    private Date startTimestamp;
    // session最后访问时间
    private Date lastAccessTime;
    // 超时时间
    private Long timeout;
    // 所在地
    private String location;

}
