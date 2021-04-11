package com.ucmed.rabbitmq.basicuse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MsgController {

    @Autowired
    private MsgProducer msgProducer;

    @GetMapping("msg/producer")
    public void createMsg() {
        for (int i = 0; i < 20; i++) {
            msgProducer.sendMsg("hello rabbitmq:" + i);

        }
    }
}
