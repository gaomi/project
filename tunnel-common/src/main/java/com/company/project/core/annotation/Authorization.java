package com.company.project.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 在controller的方法中使用此注解，检查是否登录，未登录返回401 unauthorized
 * @see authorization.interceptor.AuthorizationInterceptor
 * @Author:
 * @Date: Created in
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
}
