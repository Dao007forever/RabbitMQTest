package com.dao.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
        RabbitTemplate rt = context.getBean(RabbitTemplate.class);
        ConfirmCallback cc = context.getBean(ConfirmCallback.class);
        rt.setConfirmCallback(cc);

        MessageSender sender = context.getBean(MessageSender.class);
        for (int i = 0; i < 10; i++) {
            sender.send("Test");
        }

        System.out.println("OK");
    }
}