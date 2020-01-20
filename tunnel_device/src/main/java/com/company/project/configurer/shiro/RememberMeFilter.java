/**
 *
 */
package com.company.project.configurer.shiro;

import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * Copyright (C),2017, Guangzhou sys info. Co., Ltd.
 *
 * @author lao
 * @version 1.00
 * @ClassName: RememberMeFilter
 * @Description: 实现RememberMe过滤器
 * @date 2018年1月12日上午11:58:40
 */
public class RememberMeFilter extends FormAuthenticationFilter {
    protected final static Logger logger = LoggerFactory.getLogger(RememberMeFilter.class);

    @Resource
    private SysUserService sysUserService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Subject subject = getSubject(request, response);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        /**满足三个条件：选择记住我,非密码登录,session为空*/
        if (!subject.isAuthenticated() && subject.isRemembered() && session.getAttribute("user") == null) {
            SysUser principal = (SysUser) subject.getPrincipal();
            //String username = (String) subject.getPrincipal();
            if (!StringUtils.equals(principal.getUserName(), null)) {
                SysUser user = sysUserService.findByName(principal.getUserName());
                session.setAttribute("user", user.getUserName());
            }
        }
        return subject.isAuthenticated() || subject.isRemembered();
    }


}
