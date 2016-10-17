package me.gking2224.mstemplate.jms;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);
    
    @JmsListener(destination="SystemEvents")
    public void onMessage(TextMessage message) throws JMSException {
        LOG.debug("Received message {}", message.getText());
    }
    
}
