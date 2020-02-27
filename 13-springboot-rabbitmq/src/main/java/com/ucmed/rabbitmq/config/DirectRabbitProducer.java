package com.ucmed.rabbitmq.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * rabbitmq生产者
 */
@RequestMapping("rabbitmq")
@RestController
public class DirectRabbitProducer {

    @Autowired
    RabbitTemplate rabbitTemplate; // 使用RabbitTemplate,这提供了接受/发送等等方法

    @GetMapping("send")
    public String send() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);

        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        System.out.println("生产者产生消息:" + map.toString());
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }



}
