package com.ucmed.rocketmq.demo1;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        topic = Demo01Message.TOPIC,
        consumerGroup = "demo01-consumer-group-" + Demo01Message.TOPIC
)
@Slf4j
public class Demo01Consumer implements RocketMQListener<Demo01Message> {


    @Override
    public void onMessage(Demo01Message message) {
        log.info("[消费成功][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
