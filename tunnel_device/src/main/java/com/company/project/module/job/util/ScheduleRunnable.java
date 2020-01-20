package com.company.project.module.job.util;

import com.company.project.core.util.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 执行定时任务线程
 *
 * @author Chen
 * @created 2019-05-28-15:58.
 */
public class ScheduleRunnable implements Callable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Object target;
    private Method method;
    private String params;

    ScheduleRunnable(String beanName, String methodName, String params)
            throws NoSuchMethodException, SecurityException {
        this.target = SpringContextUtils.getBean(beanName);
        this.params = params;

        if (StringUtils.isNotBlank(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

//    @Override
//    public void run() throws Exception {
//    }

    @Override
    public Object call() {
        ReflectionUtils.makeAccessible(method);
        String result = "";
        try {
            if (StringUtils.isNotBlank(params)) {
                result = (String)method.invoke(target, params);
            } else {
                result = (String)method.invoke(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
