package com.jas.rocketmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * Created by Administrator on 2017/11/26.
 */
public class producter {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producter = new DefaultMQProducer("productergroup");
        producter.setNamesrvAddr("192.168.0.12:9876;192.168.0.14:9876");
        producter.setInstanceName("producer");
        producter.start();
        for(int i=0;i<50;i++){
            Message meg = new Message("topic", "tags", new String("第" + i + "消息").getBytes());
            SendResult send = producter.send(meg);
            System.out.println(send);
        }
        producter.shutdown();
    }
}
