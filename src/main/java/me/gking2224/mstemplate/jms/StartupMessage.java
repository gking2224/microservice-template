package me.gking2224.mstemplate.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class StartupMessage implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired @Qualifier("pubSubTemplate") JmsTemplate jmsTemplate;
    
    @Autowired @Qualifier("SystemEvents") Topic topic;
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        jmsTemplate.send(topic, new MessageCreator() {
            
            @Override
            public Message createMessage(Session session) throws JMSException {
                
                TextMessage m = session.createTextMessage("App started");
                m.setJMSDestination(topic);
                return m;
            }
        });
    }

}
