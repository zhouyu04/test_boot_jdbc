package com.zzyy.interceptor;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: zhouyu
 * @Date: 2020/7/23 17:05
 * @Description:
 */
public class JResponse<T> implements Serializable {

    private static final long serialVersionUID = 8166767658898756999L;
    @ApiModelProperty("200 正常，其它错误")
    protected boolean success = true;
    private int result = 200;
    protected String errorCode = "success";
    @ApiModelProperty("提示信息")
    protected String msg;
    @ApiModelProperty("请求ID")
    protected String requestid;
    @ApiModelProperty("返回实体")
    protected T data;

    public static <T> JResponse<T> createRsp() {
        return new JResponse();
    }

    public static <T> JResponse<T> createRsp(String errorCode, String msg) {
        return new JResponse(errorCode, msg);
    }

    public static <T> JResponse<T> createRsp(T data, String errorCode, String msg) {
        return new JResponse(data, errorCode, msg);
    }

    public static <T> JResponse<T> createRsp(T data) {
        return new JResponse(data, "success", (String) null);
    }

    protected JResponse(String errorCode, String msg) {
        this.success = "success".equals(errorCode);
        this.errorCode = errorCode;
        this.msg = msg;
        if (!this.success) {
            this.result = 60001;
        }

    }

    protected JResponse(T data, String errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
        this.success = "success".equals(errorCode);
        if (!this.success) {
            this.result = 60001;
        }

    }

    public JResponse() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public int getResult() {
        return this.result;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getRequestid() {
        return this.requestid;
    }

    public T getData() {
        return this.data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof JResponse)) {
            return false;
        } else {
            JResponse<?> other = (JResponse) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isSuccess() != other.isSuccess()) {
                return false;
            } else if (this.getResult() != other.getResult()) {
                return false;
            } else {
                label64:
                {
                    Object this$errorCode = this.getErrorCode();
                    Object other$errorCode = other.getErrorCode();
                    if (this$errorCode == null) {
                        if (other$errorCode == null) {
                            break label64;
                        }
                    } else if (this$errorCode.equals(other$errorCode)) {
                        break label64;
                    }

                    return false;
                }

                label57:
                {
                    Object this$msg = this.getMsg();
                    Object other$msg = other.getMsg();
                    if (this$msg == null) {
                        if (other$msg == null) {
                            break label57;
                        }
                    } else if (this$msg.equals(other$msg)) {
                        break label57;
                    }

                    return false;
                }

                Object this$requestid = this.getRequestid();
                Object other$requestid = other.getRequestid();
                if (this$requestid == null) {
                    if (other$requestid != null) {
                        return false;
                    }
                } else if (!this$requestid.equals(other$requestid)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof JResponse;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + (this.isSuccess() ? 79 : 97);
        result = result * 59 + this.getResult();
        Object $errorCode = this.getErrorCode();
        result = result * 59 + ($errorCode == null ? 43 : $errorCode.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $requestid = this.getRequestid();
        result = result * 59 + ($requestid == null ? 43 : $requestid.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "JResponse(success=" + this.isSuccess() + ", result=" + this.getResult() + ", errorCode=" + this.getErrorCode() + ", msg=" + this.getMsg() + ", requestid=" + this.getRequestid() + ", data=" + this.getData() + ")";
    }
}
