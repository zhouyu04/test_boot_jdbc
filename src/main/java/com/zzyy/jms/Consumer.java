package com.zzyy.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.IOException;

/**
 * @Auther: zhouyu
 * @Date: 2019/1/14 16:07
 * @Description:
 */
@Component
public class Consumer {

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "queue-product-demo")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:"+text);
    }



//    public static void main(String[] args) throws JMSException {
//        //1 创建消息中间件工厂activeMQ
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://47.107.171.112:61616");
//        //2 根据工厂获取连接
//        Connection connection = factory.createConnection();
//        //3 开启连接
//        connection.start();
//        //4 根据连接创建session
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        //5 创建队列对象
//        Queue queue = session.createQueue("queue-product-demo");
//        //6 创建消费者
//        MessageConsumer consumer = session.createConsumer(queue);
//        //7 监听消息
//        consumer.setMessageListener(new MessageListener() {
//            public void onMessage(Message message) {
//                try {
//                    TextMessage textMessage = (TextMessage) message;
//                    System.out.println("接收到了消息:" + textMessage.getText());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //关闭资源
//        session.close();
//        connection.close();
//    }
}
