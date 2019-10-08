package com.zzyy.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: zhouyu
 * @Date: 2019/8/12 17:12
 * @Description:
 */
@Data
public class Message {


    private String objectId;

    private String content;

    private LocalDateTime lastModifiedTime;

    private LocalDateTime createdTime;
}
