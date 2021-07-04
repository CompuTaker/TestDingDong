package com.example.demo.jmstemplate;

import java.net.URI;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.jms.core.JmsTemplate;

public class SpringJmsConsumer implements MessageListener {
	
	private JmsTemplate jmsTemplate;
    private Destination destination;
    
    public SpringJmsConsumer(JmsTemplate jmsTemplate) {
		// TODO Auto-generated constructor stub
    	this.jmsTemplate = jmsTemplate;
    	this.setDestination(new org.apache.activemq.command.ActiveMQQueue("messageQueue1"));
	}
    
    public BrokerService initialize(int companyIdx) {
    	this.jmsTemplate.setDefaultDestination(new ActiveMQQueue(companyIdx + ""));
    	this.setDestination(this.jmsTemplate.getDefaultDestination());
    	try {
    		return BrokerFactory.createBroker(new URI("broker://(tcp://localhost:61617)"));
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
     
    public Destination getDestination() {
        return destination;
    }
    
    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    
    public String receiveMessage() throws JMSException {
    	System.out.println("soundAlertWhenReceived~~~~");
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);       
        return textMessage.getText();
    }
    
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		System.out.println("soundAlertWhenReceived");
		System.out.println(message);
	}
    
}
