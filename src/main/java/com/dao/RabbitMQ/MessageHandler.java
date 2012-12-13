package com.dao.RabbitMQ;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

public class MessageHandler implements ChannelAwareMessageListener {
    volatile int ack = 0;

    @Override
    public void onMessage(Message message, Channel channel) {
        System.out.println("Received message: " + message);
        System.out.println("Text: " + new String(message.getBody()));
        System.out.flush();

        if ((++ack) % 2 == 1) {
            System.out.println("Nack");
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
