package com.zzyy.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class CustomControllerAdvice {

    private static Logger logger = LoggerFactory.getLogger(CustomControllerAdvice.class);


    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 500);
        map.put("msg", ex.getMessage());
        logger.error("未知异常",ex);
        return map;
    }


    /**
     * 自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    public Map CustomExceptionHandler(CustomException ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;

    }

}
