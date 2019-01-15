package com.zzyy.jms.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

/**
 * @Auther: zhouyu
 * @Date: 2019/1/15 09:41
 * @Description:
 */
@Component
public class TopicBean {

    //主题对象交给spring管理
    @Bean("topic")
    public Topic topic() {
        return new ActiveMQTopic("video.topic");
    }
}
