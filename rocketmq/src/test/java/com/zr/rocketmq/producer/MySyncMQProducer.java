package com.zr.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class MySyncMQProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("test_group");
        producer.setNamesrvAddr("192.168.1.161:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message message = new Message("MySyncTopic" /* my Topic */,
            "MySyncTag" /* my tag  */,
            ("Hello MQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            SendResult send = producer.send(message);
//            producer.sendOneway(message); /* 单向发送，不特别关注发送结果，例如日志发送 */
            System.err.printf("%s%n", send);
        }

        producer.shutdown();
    }
}
