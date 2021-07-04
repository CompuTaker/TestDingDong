package com.example.demo.controller;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jmstemplate.SpringJmsConsumer;
import com.example.demo.jmstemplate.SpringJmsProducer;

@RestController
public class HomeController {
	
	@Autowired
	SpringJmsConsumer consumer;
	
	@Autowired
	SpringJmsProducer producer;
	
	@RequestMapping(value = "/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping(value = "/dingdong")
	public String dingdong() throws JMSException {
		int companyIdx = 1;
		this.consumer.initialize(companyIdx);
		this.producer.sendRealMessage("mydingdong", companyIdx);
		this.consumer.receiveMessage();
		System.out.println("method : dingdong");
		return "dingdong";
	}
	
}
