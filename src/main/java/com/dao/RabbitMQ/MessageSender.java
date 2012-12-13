package com.dao.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
    @Autowired
    private AmqpTemplate template;

    @ManagedOperation
    public void send(String text) {
        MessageProperties mp = new MessageProperties();
        mp.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        Message msg = new Message(text.getBytes(), mp);
        template.send(msg);
    }

    @ManagedOperation
    public void send(String key, String text) {
        template.convertAndSend(key, text);
    }
}