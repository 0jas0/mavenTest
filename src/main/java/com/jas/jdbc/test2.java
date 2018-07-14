package com.jas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class test2 {
    public static void main(String[] args) {
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //获取连接
            String url = "jdbc:mysql://www.xiaoyuanlove.com.cn:3306/test?characterEncoding=UTF-8";
            String user = "root";
            String password = "juan0911";
            Connection connection = DriverManager.getConnection(url, user, password);

            //sql语句的编写
            //String sql = "insert into `sort`(`sid`,`sname`) VALUES (?,?)";


            // 获取执行平台
            //PreparedStatement preparedStatement = connection.prepareStatement(sql);
            /*preparedStatement.setInt(1,9);
            preparedStatement.setString(2, "星爷");
            int num = preparedStatement.executeUpdate();
            System.out.println(num);*/

            String sql = "select * from `sort` where sid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int sid = resultSet.getInt("sid");
                String sname = resultSet.getString("sname");
                System.out.println("sid : " + sid + ",sname：" + sname);
            }
            //关闭资源
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
