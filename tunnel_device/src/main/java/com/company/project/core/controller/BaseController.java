package com.company.project.core.controller;


import com.company.project.configurer.shiro.ShiroRealm;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.sys.model.SysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Chen
 * @created 2019-05-22-11:29.
 */
public class BaseController {
    @Autowired
    private ShiroRealm shiroRealm;

    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected SysUser getCurrentUser() {
        Object object = getSubject().getPrincipal();
        SysUser user = new SysUser();
        try {
            PropertyUtils.copyProperties(user, object);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return user;
    }

    protected Session getSession() {
        return getSubject().getSession();
    }

    protected String getSysTime() {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return format;
    }

    protected Session getSession(Boolean flag) {
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

    protected void clearAllCache() {
        shiroRealm.clearAllCache();
    }

    private Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", pageInfo.getList());
        rspData.put("total", pageInfo.getTotal());
        rspData.put("pages", pageInfo.getPages());
        return rspData;
    }

    protected Map<String, Object> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
        PageHelper.clearPage();
        return getDataTable(pageInfo);
    }
}

