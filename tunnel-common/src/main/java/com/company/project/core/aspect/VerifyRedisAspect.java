package com.company.project.core.aspect;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.annotation.VerifyRedis;
import com.company.project.core.util.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * AOP 判断Redis中是否存在数据
 *
 * @author
 */
@Aspect
@Component
public class VerifyRedisAspect {

    @Resource
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.company.project.core.annotation.VerifyRedis)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@Verify注解
            if (method.isAnnotationPresent(VerifyRedis.class)) {
                VerifyRedis annotation = method.getAnnotation(VerifyRedis.class);
                // 取出注解中的数据源名
                String redisKey = annotation.value();
                String resultInfo = null;
                try {
                    resultInfo = (String) redisUtil.get(redisKey);
                } catch (Exception e) {

                }
                if (StringUtils.isEmpty(resultInfo)) {
                    // 执行方法
                    result = point.proceed();
                } else {
                    result = JSONObject.parse(resultInfo);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
