package com.zzyy.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(SessionInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //添加登录免拦截
        if (request.getRequestURI().equals("/user/login") || request.getRequestURI().equals("/user/toLogin")) {
            return true;
        }
        //添加管理免拦截
        if (request.getRequestURI().startsWith("/monitor")) {
            return true;
        }

        if (request.getRequestURI().startsWith("/wx")) {
            return true;
        }

        Object session_user = request.getSession().getAttribute("session_user");
        if (session_user == null) {
            response.sendRedirect("/user/toLogin");
            return false;
        }
        return true;
    }


}
