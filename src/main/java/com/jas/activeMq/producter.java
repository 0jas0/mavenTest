package com.jas.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/11/16.
 */
public class producter{
    public static void main(String[] args) throws  Exception{
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "failover:(tcp://192.168.0.12:61616,tcp://192.168.0.13:61616,tcp://192.168.0.14:61616)?Randomize=false");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Destination destination = session.createQueue("queue");
        //创建生产者
        MessageProducer producer = session.createProducer(destination);
        //设置生产者模式
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //创建消息
        for(int i=0;i<5000;i++){
            TextMessage  message = session.createTextMessage();
            //发送消息
            message.setText("hello world:"+i);
            producer.send(message);
            System.out.println(message.getText());
            Thread.sleep(1000);
        }
        //关闭连接
        session.close();
        connection.close();
    }
}
