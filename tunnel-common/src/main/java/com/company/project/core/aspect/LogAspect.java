package com.company.project.core.aspect;

import com.company.project.configurer.PaodingProperties;
import com.company.project.core.ProjectConstant;
import com.company.project.core.util.HttpContextUtils;
import com.company.project.core.util.IPUtils;
import com.company.project.module.sys.model.SysLog;
import com.company.project.module.sys.model.SysUser;
import com.company.project.module.sys.service.SysLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP 记录用户操作日志
 *
 * @author MrBird
 * @link https://mrbird.cc/Spring-Boot-AOP%20log.html
 */
@Aspect
@Component
public class LogAspect {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PaodingProperties paodingProperties;

    @Autowired
    private SysLogService logService;


    @Pointcut("@annotation(com.company.project.core.annotation.Log)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws JsonProcessingException {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        String ip = IPUtils.getIpAddr(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        if (paodingProperties.isOpenAopLog()) {

            //SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            String userName = (String) request.getSession().getAttribute(ProjectConstant.SESSION_KEY);
            SysLog log = new SysLog();
            log.setUserName(userName);
            log.setOperateIp(ip);
            log.setTime(time);
            logService.saveLog(point, log);
        }
        return result;
    }
}
