package com.ucmed.rocketmq;

import com.ucmed.rocketmq.demo2.Demo02Consumer;
import com.ucmed.rocketmq.demo2.Demo02Producer;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo02ProducerTest {


    @Autowired
    private Demo02Producer producer;

    @Test
    public void testSendBatch() throws InterruptedException {
        Integer a = ((Double) (Math.random() * 100)).intValue();
        Integer b = ((Double) (Math.random() * 1000)).intValue();
        Integer c = ((Double) (Math.random() * 10000)).intValue();
        List<Integer> ids = Arrays.asList(a, b, c);
        SendResult result = producer.sendBatch(ids);
        log.info("[Demo02测试批量发送][发送编号：[{}]] 发送结果[{}]", ids, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
