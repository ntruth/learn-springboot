package com.ucmed.rocketmq;

import com.ucmed.rocketmq.demo3.Demo03Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo03ProducerTest {

    @Autowired
    private Demo03Producer producer;

    @Test
    public void testSyncSendDepay() throws InterruptedException {
        int id = (int) System.currentTimeMillis() / 100;
        SendResult result = producer.syncSendDelay(id, 3);// 延迟级别3, 即10秒后消费
        log.info("[testSyncSendDepay][发送编号:[{}] 发送接货:[{}]]", id, result);
        // 阻塞等待,保证消费
        new CountDownLatch(1).await();
    }


}
