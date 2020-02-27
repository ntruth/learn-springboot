package com.ucmed.rabbitmq.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Rabbitmq消费者
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectRabbitReceiver {
    @RabbitHandler
    public void process(Map message) {
        System.out.println("消费者收到消息: " + message.toString());
    }
}
