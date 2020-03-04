package com.ucmed.rocketmq.demo2;

import lombok.Data;

@Data
public class Demo02Message {

    public static final String TOPIC = "DEMO_02";

    /**
     * 编号
     */
    private Integer id;
}
