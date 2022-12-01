package com.zr.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class MyAsyncMQProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("test_group1");
        producer.setNamesrvAddr("192.168.1.161:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(3);
        for (int i = 0; i < 100; i++) {
            final int index = i;

            Message message = new Message("MyAsyncTopic" /* my Topic */,
            "MyAsyncTag" /* my tag  */,
            ("Hello MQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
           producer.send(message, new SendCallback() {

                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(5000L);
        //
        producer.shutdown();
    }
}
