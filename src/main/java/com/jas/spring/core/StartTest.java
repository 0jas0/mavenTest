package com.jas.spring.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <pre>
 * @Desc  :
 * @Author: juas
 * @Date  : 2019-10-10 20:15
 * </pre>
 */
public class StartTest {
    public static void main(String[] args) {
        System.setProperty("spring", "classpath");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("${spring}:spring-test.xml");
        SimpleBean simpleBean = context.getBean(SimpleBean.class);
        simpleBean.sendMsg();
        context.close();
    }
}
