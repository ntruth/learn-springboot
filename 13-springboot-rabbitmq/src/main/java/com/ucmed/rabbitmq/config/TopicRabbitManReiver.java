package com.ucmed.rabbitmq.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.man")
public class TopicRabbitManReiver {

    @RabbitHandler
    public void process(Map message) {
        System.out.println("TopicManReceiver收到消息:" + message.toString());
    }
}
