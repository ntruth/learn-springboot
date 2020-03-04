package com.ucmed.rocketmq.demo1;

import lombok.Data;

@Data
public class Demo01Message {
    public static final String TOPIC = "DEMO_01";

    private Integer id;

    private String username;

    private String password;
}
