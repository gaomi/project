package com.company.project.module.app.web;

import com.company.project.core.ProjectConstant;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.annotation.AppLog;
import com.company.project.core.controller.CommonBaseController;
import com.company.project.module.app.service.AppSysService;
import com.company.project.module.sys.model.SysDept;
import com.company.project.module.sys.model.SysUser;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基本信息接口
 *
 * @author Chen
 * @created 2019-09-19-11:30.
 */
@CrossOrigin
@RestController
@RequestMapping("/app/")
@Api(description = "手机端基本数据接口")
public class AppLoginController extends CommonBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppSysService appSysService;


    @PostMapping(value = "/loginVerify")
    @AppLog("验证是否登录")
    public Result isLogin( String userName, String passWord, HttpSession session) {
        List<SysUser> users = appSysService.isLogin(userName, passWord);
        if (!CollectionUtils.isEmpty(users)) {
            SysUser user = users.get(0);
            session.setAttribute(ProjectConstant.SESSION_KEY, user);
            HashMap<String, String> map = Maps.newHashMap();
            SysDept sysDept = appSysService.findDeptById(user.getDeptId());
            map.put("userName", user.getUserName());
            map.put("trueName", user.getTrueName());
            map.put("id", user.getId());
            map.put("telephone", user.getTelephone());
            map.put("employeeNo", user.getEmployeeNo());
            map.put("deptId", user.getDeptId());
            map.put("deptName", sysDept.getName());
            map.put("CompanyName", "监护部");
            session.setAttribute(ProjectConstant.SESSION_KEY_ID, user.getEmployeeNo());
            session.setMaxInactiveInterval(60 * 60 * 2);
            return ResultGenerator.genSuccessResult(map);
        } else {
            return ResultGenerator.genFailResult("账号密码错误!");
        }

    }

    @PostMapping(value = "/changePassword")
    @AppLog("修改密码")
    public Result changePassword(String passWord, String newPassword, HttpServletRequest request) {
        SysUser user = (SysUser) request.getSession().getAttribute(ProjectConstant.SESSION_KEY);
            List<SysUser> users = appSysService.isLogin(user.getUserName(), passWord);
            if (!CollectionUtils.isEmpty(users)) {
                Map param = Maps.newHashMap();
                SysUser user_now = users.get(0);
                param.put("id", user_now.getId());
                param.put("newPassword", DigestUtils.md5DigestAsHex(newPassword.getBytes()));
                appSysService.updateUserPassword(param);
                return ResultGenerator.genSuccessResult();
            }else{
                return ResultGenerator.genFailResult("账号密码错误!");
            }

    }

    @PostMapping(value = "/logout")
    @AppLog("清除token")
    public Result logout(HttpSession session) {
        try {
            String userId = super.verifySession(session);
        } catch (ServerException e) {
            logger.error("sesion不存在");
        }
        session.removeAttribute(ProjectConstant.SESSION_KEY_ID);
        return ResultGenerator.genSuccessResult();
    }


}
