package com.rj.idempotency.token.annotation;

import com.rj.idempotency.token.cache.MyCacheConfiguration;
import com.rj.idempotency.token.constant.HeaderConstant;
import com.rj.idempotency.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class RepeatIntercept implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            //获取方法上的参数
            RepeatLimiter repeatLimiter = AnnotationUtils.findAnnotation(((HandlerMethod) handler).getMethod(), RepeatLimiter.class);

            if (Objects.isNull(repeatLimiter)){
                //获取controller类上注解
                repeatLimiter=AnnotationUtils.findAnnotation(((HandlerMethod) handler).getBean().getClass(),RepeatLimiter.class);
            }

            //使用注解，需要拦截验证
            if (Objects.nonNull(repeatLimiter)){
                //获取全局token，表单提交的唯一id
                String tokenInfo = (String) request.getAttribute(HeaderConstant.HEADER_INFO);

                //没有携带token，抛异常，这里的异常需要全局捕获
                if (StringUtils.isEmpty(tokenInfo)) {
                    throw new RuntimeException("无token");
                }

                //校验token
                Boolean flag = tokenService.checkToken(Long.parseLong(tokenInfo));

                //抛出重复提交的异常
                if (Boolean.FALSE.equals(flag)) {
                    throw new RuntimeException("重复提交");
                }
            }
        }
        return true;
    }
}