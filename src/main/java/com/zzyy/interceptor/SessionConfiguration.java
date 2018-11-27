package com.zzyy.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SessionConfiguration implements WebMvcConfigurer {

    @Autowired
    SessionInterceptor sessionInterceptor;

    @Autowired
    LogHandlerInterceptor logHandlerInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
        registry.addInterceptor(logHandlerInterceptor).addPathPatterns("/**");
    }
}
