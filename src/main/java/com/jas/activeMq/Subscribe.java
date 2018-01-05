package com.jas.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/11/20.
 */
public class Subscribe {
    private static ConnectionFactory connectionFactory;
    private static Connection        connection;
    private static Session session;
    private static Destination destination;
    private final static String QUEUENAME = "嘻嘻";
    static {
        try {
            connectionFactory = new ActiveMQConnectionFactory("jas","jas","tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(QUEUENAME);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void consumeMessage() throws JMSException, InterruptedException {
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                try {
                    System.out.println("订阅者-订阅的消息："+ ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(1000*1000);
        /*TextMessage message = (TextMessage)consumer.receive();
        System.out.printf(message.getText());*/
        connection.close();
    }

    public static void main(String[] args) throws JMSException, InterruptedException {
        consumeMessage();
    }
}
