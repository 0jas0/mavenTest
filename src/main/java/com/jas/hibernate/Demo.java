package com.jas.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Demo {
    @Test
    public void demo1(){
        // 1.加载配置文件:
        Configuration cfg = new Configuration().configure();
        // 2.创建一个SessionFactory:
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        // 3.创建Session对象.Session对象 类似Connection.
        Session session = sessionFactory.openSession();
        // 4.开启事务:
        Transaction tx = session.beginTransaction();
        // 5.执行相关操作
        Customer customer = new Customer();
        customer.setCust_name("小王");
        customer.setCust_source("小广告");

        session.save(customer);
        // 6.事务提交
        tx.commit();
        // 7.释放资源
        session.close();

    }
}
