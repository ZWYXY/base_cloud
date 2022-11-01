package com.zr.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/p")
@RestController
public class ProducerController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/1")
    public void produce() {
        rocketMQTemplate.convertAndSend("firstTopic", "hello rocket mq");
    }


}
