package com.company.project.module.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import com.company.project.core.annotation.Log;
import com.company.project.core.util.AddressUtils;
import com.company.project.module.sys.dao.SysLogMapper;
import com.company.project.module.sys.model.SysLog;
import com.company.project.module.sys.service.SysLogService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by chen on 2019/05/21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysLogServiceImpl extends AbstractService<SysLog> implements SysLogService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public List<SysLog> findAllLogs(Map map) {
        try {
            Object keyWord = map.get("keyWord");
            Object sCreateTime = map.get("sCreateTime");
            Object eCreateTime = map.get("eCreateTime");
            Example example = new Example(SysLog.class);
            Criteria criteria = example.createCriteria();

            if (null != keyWord && StringUtils.isNotBlank(keyWord.toString())) {
                criteria.andCondition("USER_NAME like", "%" + keyWord.toString().toLowerCase() + "%");
            }
            //if (null != log.getOperation()) {
            //    criteria.andCondition("operation like ", "%" + log.getOperation() + "%");
            //}
            if (null != sCreateTime && StringUtils.isNotBlank(sCreateTime.toString())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(sCreateTime.toString());
                criteria.andCondition(" create_time >=", date);
                //criteria.andCondition("date_format(create_time,'%Y-%m-%d') <=", timeArr[1]);
            }
            if (null != eCreateTime && StringUtils.isNotBlank(eCreateTime.toString())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(eCreateTime.toString());
                //String[] timeArr = sCreateTime.toString().split("~");
                //criteria.andCondition("date_format(create_time,'%Y-%m-%d') >=", timeArr[0]);
                criteria.andCondition(" create_time  <=", date);
            }
            example.setOrderByClause("create_time desc");
            return this.findByExample(example);
        } catch (Exception e) {
            logger.error("获取系统日志失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<SysLog> findAllLogs(SysLog log) {
        try {
            Example example = new Example(SysLog.class);
            Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(log.getUserName())) {
                criteria.andCondition("user_name=", log.getUserName().toLowerCase());
            }
            if (null != log.getOperation()) {
                criteria.andCondition("operation like", "%" + log.getOperation() + "%");
            }
            if (StringUtils.isNotBlank(log.getTimeField())) {
                String[] timeArr = log.getTimeField().split("~");
                criteria.andCondition("date_format(create_time,'%Y-%m-%d') >=", timeArr[0]);
                criteria.andCondition("date_format(create_time,'%Y-%m-%d') <=", timeArr[1]);
            }
            example.setOrderByClause("create_time desc");
            return this.findByExample(example);
        } catch (Exception e) {
            logger.error("获取系统日志失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void saveLog(ProceedingJoinPoint joinPoint, SysLog log) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);
        log.setId(UUID.randomUUID().toString());
        if (logAnnotation != null) {
            // 注解上的描述
            log.setOperation(logAnnotation.value());
        }
        // 请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(paramNames));
            log.setParams(params.toString());
        }
        log.setCreateTime(new Date());
        log.setLocation(AddressUtils.getCityInfo(log.getOperateIp()));
        save(log);
    }


    @SuppressWarnings("unchecked")
    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List list = new ArrayList();
                List paramList = new ArrayList<>();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params, list.toArray(), paramList);
            } else {
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出NoSuchMethodException 异常则存在 toString 方法 ，安全的writeValueAsString ，否则 走 Object的 toString方法
                        params.append("  ").append(paramNames.get(i)).append(": ").append(JSONObject.toJSONString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append("  ").append(paramNames.get(i)).append(": ").append(JSONObject.toJSONString(args[i].toString()));
                    }
                } else if (args[i] instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) args[i];
                    params.append("  ").append(paramNames.get(i)).append(": ").append(file.getName());
                } else {
                    params.append("  ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return params;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteAll() {
        sysLogMapper.deleteAllLogs();
    }
}
