package com.zr.rocketmq.producer.scheduled;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

public class ScheduledProducer {

    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("my-scheduled-producer-group");
        producer.setNamesrvAddr("192.168.1.161:9876");

        producer.start();
        int totalMessageToSend = 100;
        for (int i = 0; i < totalMessageToSend; i++) {
            Message msg = new Message("MyScheduledTopic", ("Hello scheduled message" + i).getBytes());
            msg.setDelayTimeLevel(3);
            // 设置延时等级3,这个消息将在10s之后发送(现在只支持固定的几个时间,详看delayTimeLevel)
            SendResult send = producer.send(msg);
            System.err.println("Send Result: " + send.getSendStatus());
        }

//        TimeUnit.SECONDS.sleep(20);
//        producer.shutdown();
    }

}
