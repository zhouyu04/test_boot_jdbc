package com.zzyy.entity;

import java.util.Date;

public class BootLog {
    private Long logId;

    private String logClientIp;

    private String logUrl;

    private String logMethod;

    private String logParam;

    private String logSession;

    private Date logTime;

    private Date logReturnTime;

    private String logReturnData;

    private Long logReturnStatus;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLogClientIp() {
        return logClientIp;
    }

    public void setLogClientIp(String logClientIp) {
        this.logClientIp = logClientIp == null ? null : logClientIp.trim();
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl == null ? null : logUrl.trim();
    }

    public String getLogMethod() {
        return logMethod;
    }

    public void setLogMethod(String logMethod) {
        this.logMethod = logMethod == null ? null : logMethod.trim();
    }

    public String getLogParam() {
        return logParam;
    }

    public void setLogParam(String logParam) {
        this.logParam = logParam == null ? null : logParam.trim();
    }

    public String getLogSession() {
        return logSession;
    }

    public void setLogSession(String logSession) {
        this.logSession = logSession == null ? null : logSession.trim();
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Date getLogReturnTime() {
        return logReturnTime;
    }

    public void setLogReturnTime(Date logReturnTime) {
        this.logReturnTime = logReturnTime;
    }

    public String getLogReturnData() {
        return logReturnData;
    }

    public void setLogReturnData(String logReturnData) {
        this.logReturnData = logReturnData == null ? null : logReturnData.trim();
    }

    public Long getLogReturnStatus() {
        return logReturnStatus;
    }

    public void setLogReturnStatus(Long logReturnStatus) {
        this.logReturnStatus = logReturnStatus;
    }
}