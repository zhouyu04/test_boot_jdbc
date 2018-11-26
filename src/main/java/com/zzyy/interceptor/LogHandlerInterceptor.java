package com.zzyy.interceptor;

import com.alibaba.fastjson.JSON;
import com.zzyy.entity.BootLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogHandlerInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(LogHandlerInterceptor.class);

    /**
     * 前置
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BootLog bl = new BootLog();

        String requestedSessionId = request.getRequestedSessionId();
        String requestURI = request.getRequestURI();
        String paramter = JSON.toJSONString(request.getParameterMap());
        //客户端ip
//        request.get


        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    /**
     * 后置
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
