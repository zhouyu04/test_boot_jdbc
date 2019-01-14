package com.zzyy.test_boot_jdbc;

import com.zzyy.jms.Product;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBootJdbcApplicationTests {
	@Autowired
	private Product producer;

	@Test
	public void contextLoads() {

		Destination destination = new ActiveMQQueue("queue-product-demo");

		for(int i=0; i<100; i++){
			producer.sendMessage(destination, "myname is chhliu!!!");
		}
	}

}
