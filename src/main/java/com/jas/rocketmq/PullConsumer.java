package com.jas.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PullConsumer{
    private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();


    public static void main(String[] args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("consumergroup");
        consumer.setNamesrvAddr("192.168.0.12:9876;192.168.0.14:9876");
        consumer.start();
        //获取订阅topic的queue
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("topic");
        for (MessageQueue mq : mqs) {
            System.out.println("Consume from the queue: " + mq);
            SINGLE_MQ:
            while (true) {
                try {//阻塞的拉去消息，中止时间默认20s
                    PullResult pullResult =
                            consumer.pullBlockIfNotFound(mq, "tags", getMessageQueueOffset(mq), 32);
                    System.out.println(Thread.currentThread().getName()+new Date()+""+pullResult);
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
                        case FOUND://pullSataus
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        consumer.shutdown();
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offseTable.get(mq);
        if (offset != null)
            return offset;

        return 0;
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offseTable.put(mq, offset);
    }

}