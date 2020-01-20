package com.company.project.module.sys.web;

import com.company.project.configurer.shiro.ShiroUtils;
import com.company.project.core.ProjectConstant;
import com.company.project.core.annotation.Log;
import com.company.project.core.controller.BaseController;
import com.company.project.core.controller.CommonBaseController;
import com.company.project.core.util.redis.JedisUtils;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author Chen
 * @created 2019-08-22-11:24.
 */
@Controller
public class LoginController extends BaseController {
    protected final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @Resource
    SysUserService sysUserService;

    /**
     * @param um         登录账号
     * @param pw         登录密码
     * @param rememberMe 记住我
     * @param request
     * @return
     * @throws
     * @Description: 登录认证
     * @author lao
     * @Date 2018年1月15日下午12:24:19
     * @version 1.00
     */
    @Log("用户登录")
    @RequestMapping(value = "/submitLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(String um, String pw, boolean rememberMe, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(um, ShiroUtils.getStrByMD5(pw));
        //String str = ShiroUtils.getStrByMD5(pw);
        try {
            token.setRememberMe(rememberMe);
            subject.login(token);
            JedisUtils.del(um);
            logger.info("------------------身份认证成功-------------------");
            request.getSession().setAttribute(ProjectConstant.SESSION_KEY, um);
            request.getSession().setMaxInactiveInterval(2 * 60 * 60);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功！");
        } catch (DisabledAccountException dax) {
            logger.info("用户名为:" + um + " 用户已经被禁用！");
            resultMap.put("status", 500);
            resultMap.put("message", "帐号已被禁用！");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("用户名为:" + um + " 用户登录次数过多，有暴力破解的嫌疑！");
            resultMap.put("status", 500);
            resultMap.put("message", "登录次数过多！");
        } catch (AccountException ae) {
            logger.info("用户名为:" + token.getPrincipal() + " 帐号或密码错误！");
            String excessiveInfo = ExcessiveAttemptsInfo(um);
            if (null != excessiveInfo) {
                resultMap.put("status", 500);
                resultMap.put("message", excessiveInfo);
            } else {
                resultMap.put("status", 500);
                resultMap.put("message", "帐号或密码错误！");
            }
        } catch (AuthenticationException ae) {
            logger.error("身份认证失败！", ae);
            logger.info("------------------身份认证失败-------------------");
            resultMap.put("status", 500);
            resultMap.put("message", "帐号或密码错误！");
        } catch (Exception e) {
            logger.error("登录认证错误！", e);
            logger.info("未知异常信息。。。。");
            resultMap.put("status", 500);
            resultMap.put("message", "登录认证错误！");
        }
        return resultMap;
    }


    /**
     * @return
     * @throws
     * @Description: 退出
     * @author lao
     * @Date 2018年1月11日下午4:23:35
     * @version 1.00
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (Exception e) {
            logger.error("errorMessage:" + e.getMessage());
        }
        return "redirect:login";
    }


    /**
     * @param um 登录账号
     * @throws
     * @Description: 验证器，增加了登录次数校验功能
     * @author lao
     * @Date 2018年1月15日下午12:03:18
     * @version 1.00
     */
    public String ExcessiveAttemptsInfo(String account) {
        String excessiveInfo = null;
        StringBuffer userName = new StringBuffer(account);
        userName.append("ExcessiveCount");
        String accountKey = userName.toString();

        if (null == JedisUtils.get(accountKey)) {
            JedisUtils.setex(accountKey, 1800, "1");
        } else {
            int count = Integer.parseInt(JedisUtils.get(accountKey)) + 1;
            JedisUtils.setex(accountKey, 1800 - (120 * count), Integer.toString(count));
        }
        /**登录错误5次,发出警告*/
        if (Integer.parseInt(JedisUtils.get(accountKey)) == 5) {
            excessiveInfo = "账号密码错误5次,再错5次账号将被禁用！";
        }
        /**登录错误10次,该账号将被禁用*/
        if (Integer.parseInt(JedisUtils.get(accountKey)) == 10) {
            SysUser pojo = new SysUser();
            pojo.setUserName(account);
            SysUser au = sysUserService.findByName(pojo.getUserName());
            if (null != au) {
                sysUserService.updateDisabled(au.getId(), true);
            }
            JedisUtils.del(account);
            excessiveInfo = "账号密码错误过多,账号已被禁用！";
        }
        return excessiveInfo;
    }

}