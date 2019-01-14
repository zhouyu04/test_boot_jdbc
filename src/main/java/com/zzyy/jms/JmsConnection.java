package com.zzyy.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;

/**
 * @Auther: zhouyu
 * @Date: 2019/1/14 10:18
 * @Description:
 */
public enum  JmsConnection  {

    INSTANCE;

    private ConnectionFactory factory = null;
    private JmsConnection(){
        System.out.println("创建连接......");
        factory = new ActiveMQConnectionFactory("tcp://47.107.171.112:61616");
    }

    public ConnectionFactory getConnection() {
        return factory;
    }
}
