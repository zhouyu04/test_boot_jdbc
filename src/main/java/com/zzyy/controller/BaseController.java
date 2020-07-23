package com.zzyy.controller;

import com.zzyy.interceptor.JResponse;

/**
 * @Auther: zhouyu
 * @Date: 2020/7/23 17:33
 * @Description:
 */
public class BaseController {
    public BaseController() {
    }

    public JResponse<Object> result() {
        return BaseResponse.createRsp();
    }

    public JResponse<Object> result(Object data, String errorCode) {
        JResponse<Object> rsp = BaseResponse.createRsp();
        rsp.setData(data);
        rsp.setErrorCode(errorCode);
        return rsp;
    }

    public JResponse<Object> result(Object data, String errorCode, String returnMsg) {
        JResponse<Object> rsp = BaseResponse.createRsp(errorCode, returnMsg);
        rsp.setData(data);
        return rsp;
    }

    public JResponse<Object> result(String errorCode, String errorMsg) {
        return BaseResponse.createRsp(errorCode, errorMsg);
    }

    public <T> JResponse<T> result(T data) {
        return JResponse.createRsp(data);
    }

    public JResponse<Object> resultObj(Object data) {
        JResponse<Object> rsp = BaseResponse.createRsp();
        rsp.setData(data);
        return rsp;
    }

}
