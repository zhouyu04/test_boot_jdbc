package com.zzyy.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/26 17:19
 * @Description:
 */
@Data
public class WxVerifyTicket {

    private String appId;

    private String infoType;

    private String ticket;

    private String authorizerAppid;

    private String authorizationCode;

    private String preAuthCode;

    private Date createTime;

    private Date modifyTime;


}
