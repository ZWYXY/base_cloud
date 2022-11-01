package com.zr.rocketmq;


import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "group1", topic = "firstTopic")
public class Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String o) {
        System.err.println(o);
    }
}
