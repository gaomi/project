package com.company.project.core.controller;


import com.company.project.core.ProjectConstant;
import com.company.project.core.model.QueryRequest;
import com.company.project.module.sys.model.SysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Chen
 * @created 2019-05-22-11:29.
 */
public class CommonBaseController {

    protected SysUser getWebUser(HttpSession session) throws ServerException {
        SysUser user = (SysUser) session.getAttribute(ProjectConstant.SESSION_KEY);
        if (user.equals(null)) {
            throw new ServerException("请先登录");
        }
        return user;
    }

    protected String verifySession(HttpSession session) throws ServerException {
        String userId = (String) session.getAttribute(ProjectConstant.SESSION_KEY_ID);
        if (userId.equals(null)) {
            throw new ServerException("请先登录");
        }
        return userId;
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

