package com.zzyy.test_boot_jdbc;

import com.zzyy.jms.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Topic;

/**
 * @Auther: zhouyu
 * @Date: 2019/1/15 10:04
 * @Description:
 */
public class TestMq {


    @Test
    public void testTopic(){
        Topic topic = new Topic() {
            @Override
            public String getTopicName() throws JMSException {
                return "topic-demo";
            }
        };
        Product producer = new Product();
        for (int i = 0;i<50;i++){
            producer.sendTopic(topic,"第一测试");
        }
    }
}
