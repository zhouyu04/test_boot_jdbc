package com.zzyy.test_boot_jdbc;

import com.zzyy.jms.Product;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Topic;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBootJdbcApplicationTests {
	@Autowired
	private Product producer;

	@Test
	public void contextLoads() {

		//-----------------------------测试点对点-------------------------------------
//		Destination destination = new ActiveMQQueue("queue-product-demo");
//		Destination destination2 = new ActiveMQQueue("consumer-2");
//		for(int i=0; i<10; i++){
//			producer.sendMessage(destination, "myname is chhliu!!!");
//		}
//		for(int i=0; i<10; i++){
//			producer.sendMessage(destination2, "myname is zzyy!!!");
//		}
		//-----------------------------测试点对点-------------------------------------

		Topic topic = new Topic() {
			@Override
			public String getTopicName() throws JMSException {
				return "topic-demo";
			}
		};

		for (int i = 0;i<50;i++){
			producer.sendTopic(topic,"第一测试");
		}


	}

}
