package com.example.demo.jmstemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class SpringJmsProducer {
	
	private JmsTemplate jmsTemplate;
	
	private Destination destination; // default destination -> messageQueue1
	
	public SpringJmsProducer(JmsTemplate jmsTemplate) {
		// TODO Auto-generated constructor stub
    	this.jmsTemplate = jmsTemplate;
    	this.setDestination(new org.apache.activemq.command.ActiveMQQueue("messageQueue1"));
	}
    
	public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
	
    public Destination getDestination() {
        return this.destination;
    }
    
    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    
    public void sendMessage(final String msg) {
        System.out.println("Producer sends " + msg);
        this.jmsTemplate.send(this.destination, new MessageCreator() {
        	@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
    
    public void sendRealMessage(final String msg, final int companyIdx) {
        System.out.println("Producer sends " + msg);
        
        // company + "" // send a message to specified destination
        this.jmsTemplate.send(companyIdx + "", new MessageCreator() {
        	@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg); // 그대로 실행!
            }
        });
    }
    
}
