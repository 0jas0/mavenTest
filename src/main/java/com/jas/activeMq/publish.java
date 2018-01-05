package com.jas.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/11/20.
 */
public class publish {
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
    public static void send(String message) throws JMSException {
        MessageProducer producer = session.createProducer(null);
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(message);
        producer.send(destination,textMessage);
        connection.close();
    }

    public static void main(String[] args) throws JMSException {
        send("haha11111");
    }
}
