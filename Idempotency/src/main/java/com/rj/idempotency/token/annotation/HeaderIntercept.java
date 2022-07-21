package com.rj.idempotency.token.annotation;

import com.rj.idempotency.token.constant.HeaderConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class HeaderIntercept implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String token = request.getHeader(HeaderConstant.TOKEN);
        //校验逻辑
        if (!validToken(token)) {
            throw new RuntimeException("TOKEN失效");
        }
        //放入request中
        request.setAttribute(HeaderConstant.HEADER_INFO,token);
        return true;
    }

    /**
     * 校验token，逻辑自己实现
     * @param token
     * @return
     */
    private boolean validToken(String token){
        return Boolean.TRUE;
    }
}