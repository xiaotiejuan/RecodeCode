package com.rj.idempotency.token.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private HeaderIntercept headerIntercept;

    @Autowired
    private RepeatIntercept repeatIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	// 添加拦截器，配置拦截地址
        registry.addInterceptor(headerIntercept).addPathPatterns("/token/**");
        registry.addInterceptor(repeatIntercept).addPathPatterns("/token/**");
    }
}
