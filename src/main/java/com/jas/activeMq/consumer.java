package com.jas.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/11/16.
 */
public class consumer {
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
        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //接受消息
        while (true){
            TextMessage mes = (TextMessage)consumer.receive();
            if(mes==null){
                break;
            }
            System.out.println(mes.getText());
        }
        //关闭连接
        session.close();
        connection.close();
    }
}
