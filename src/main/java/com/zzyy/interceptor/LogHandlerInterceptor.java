package com.zzyy.interceptor;

import com.alibaba.fastjson.JSON;
import com.zzyy.entity.BootLog;
import com.zzyy.mapper.BootLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.zzyy.utils.IPUtils.getIPClient;

@Component
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
        String ipClient = getIPClient(request);
        String method = request.getMethod();
        bl.setLogClientIp(ipClient);
        bl.setLogMethod(method);
        bl.setLogParam(paramter);
        bl.setLogUrl(requestURI);
        bl.setLogSession(requestedSessionId);

        BootLogMapper bootLogMapper = getDao(BootLogMapper.class, request);
        log.info("添加日志" + bl.toString());
        bootLogMapper.insert(bl);
        return true;
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


        int status = response.getStatus();

    }


    /**
     * 功能描述:
     * 在拦截器无法使用自动注入
     * 通过该方法获得的applicationContext 已经是初始化之后的applicationContext 容器
     *
     * @auther: zhouyu
     * @date: 2018/11/27 20:46
     */
    public <T> T getDao(Class<T> clazz, HttpServletRequest request) {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return applicationContext.getBean(clazz);
    }

}
