package com.zzyy.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Topic;

/**
 * @Auther: zhouyu
 * @Date: 2019/1/14 10:28
 * @Description:
 */
@Service("product")
public class Product {


    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message) {
        jmsTemplate.convertAndSend(destination, message);
    }


    public void sendTopic(Topic topic,final String message){

        jmsTemplate.convertAndSend(topic,message);
    }







//    public static void main(String[] args) throws JMSException {
//        //1 创建工厂
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://47.107.171.112:61616");
//        // 2 获取连接对象
//        Connection connection = factory.createConnection();
//        // 3 开启连接
//        connection.start();
//        //4 根据连接对象获取session
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        //5 根据ssession创建队列对象,封装目的地名称
//        Queue queue = session.createQueue("queue-product-demo");
//        //6 根据session创建生产者
//        MessageProducer producer = session.createProducer(queue);
//        //7 创建并发送消息
//        TextMessage textMessage = session.createTextMessage("我的第二个jms,消息中间件");
//        producer.send(textMessage);
//        //关闭资源
//        session.close();
//        connection.close();
//    }

}
