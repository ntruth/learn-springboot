package com.ucmed.rabbitmq.basicuse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 负载均衡的消费者
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiver1 {
    @RabbitHandler
    public void process(String content) {
        log.info("222接收处理队列A当中的消息： " + content);
    }
}
