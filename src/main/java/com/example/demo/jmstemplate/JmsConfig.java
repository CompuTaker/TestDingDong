package com.example.demo.jmstemplate;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {
	
	@Bean(name = "jmstemplate")
	public JmsTemplate jmsTemplate() {
		// TODO Auto-generated constructor stub
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(new ActiveMQConnectionFactory("tcp://localhost:61616"));
		jmsTemplate.setReceiveTimeout(10000);
		return jmsTemplate;
	}
	
	@Bean(name = "consumer")
	SpringJmsConsumer springJmsConsumer() {
		SpringJmsConsumer springJmsConsumer = new SpringJmsConsumer(this.jmsTemplate());
		return springJmsConsumer;
	}
	
	@Bean(name = "producer")
	SpringJmsProducer springJmsProducer() {
		SpringJmsProducer springJmsProducer = new SpringJmsProducer(this.jmsTemplate());
		return springJmsProducer;
	}
	
}
