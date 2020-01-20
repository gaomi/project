package com.company.project.configurer;

import com.alibaba.fastjson.JSON;
import com.company.project.configurer.token.TokenManager;
import com.company.project.core.ProjectConstant;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.annotation.Authorization;
import com.company.project.core.annotation.CurrentUserId;
import com.company.project.core.exception.ServiceException;
import com.company.project.module.api.model.TokenModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Spring MVC 配置
 */
@EnableWebMvc
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    @Autowired
    private TokenManager manager;

    /**
     * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面了
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/module/sys/sideMenu").setViewName("module/sys/sideMenu");
        registry.addViewController("/module/data/sideMenu").setViewName("module/data/sideMenu");
        registry.addViewController("/error/errordeal").setViewName("error/errordealogin");
        registry.addViewController("/error/unauthorized").setViewName("error/unauthorized");
        super.addViewControllers(registry);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //指定了静态资源文件的位置
        //registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
        //registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
        //registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/static/images/");

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/map/**").addResourceLocations("classpath:/map/");

        //swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }


    /**
     * 关闭大小写
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }


    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                    logger.info(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    /**
     * 重写签名拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                    //        如果不是映射到方法直接通过
                    if (!(handler instanceof HandlerMethod)) {
                        return true;
                    }
                    String url = request.getRequestURI();
                    if (!(url.split("/")[1].equals("api"))){
                        return true;
                    }
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    Method method = handlerMethod.getMethod();
                    boolean pass = validateSign(request, response, method);
                    if (pass) {
                        return true;
                    } else {
                        //logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                        //        request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                        //
                        //Result result = new Result();
                        //result.setCode(ResultCode.UNAUTHORIZED).setMessage("签名认证失败");
                        //responseResult(response, result);
                        return false;
                    }
                }
            });
    }
    /**
     * 重写签名认证，规则：
     * 在header中加入authorization字段。
     */
    private boolean validateSign(HttpServletRequest request, HttpServletResponse response, Method method) {
        String url = request.getRequestURI();
        logger.info("请求的url为:" + url);
        //        从header中得到token
        String authorization = request.getHeader(ProjectConstant.AUTHORIZATION);
//        验证token
        TokenModel model = manager.getToken(authorization);
        if (method.getAnnotation(Authorization.class) != null) {
            if (null == model) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            // 不是 admin 不能访问 /admin 的接口
            if (url.split("/")[1].equals("admin")) {
                if (String.valueOf(model.getUserId()).length() != 3) {
                    System.out.println("不是 admin 不能访问 /admin 的接口");
                    logger.warn("禁止的admin 的访问，请求接口：{}，请求IP：{}，请求参数：{}",
                            request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                    Result result = new Result();
                    result.setCode(ResultCode.FORBIDDEN).setMessage("禁止访问");
                    responseResult(response, result);
                    return false;
                }
            }

//            if (url.split("/")[1].equals("user")) {
//                if (String.valueOf(model.getUserId()).length() != 8) {
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    return false;
//                }
//            }
        }

        if (this.manager.checkToken(model)) {
            // 如果token验证成功，将token对应的userId存在request中，便于之后注入
            request.setAttribute(ProjectConstant.CURRENT_USER_ID, model.getUserId());
            return true;
        }
        //如果验证token失败，并且方法注明了Authorization， 返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                    request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));

            Result result = new Result();
            result.setCode(ResultCode.UNAUTHORIZED).setMessage("签名认证失败");
            responseResult(response, result);
            return false;
        }
        return true;
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }

    /**
     * 参数转换器
     * @Description: 增加方法注入，将含有CurrentUser注解的方法参数注入当前userId
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter methodParameter) {
                if (methodParameter.getParameterType().isAssignableFrom(String.class) &&
                        methodParameter.hasParameterAnnotation(CurrentUserId.class)) {
                    return true;
                }
                return false;
            }

            @Override
            public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
                String currentUserId = (String) nativeWebRequest.getAttribute(ProjectConstant.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
                if (null != currentUserId) {
                    return currentUserId;
                }
                throw new MissingServletRequestPartException(ProjectConstant.CURRENT_USER_ID);
            }
        });
    }


}
