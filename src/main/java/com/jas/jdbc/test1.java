package com.jas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class test1 {
    public static void main(String[] args) {
        try {
            // 注册驱动
            //DriverManager.registerDriver(new Driver());
            Class.forName("com.mysql.jdbc.Driver");

            //获取链接
            String url = "jdbc:mysql://www.xiaoyuanlove.com.cn:3306/test?characterEncoding=UTF-8";
            String user = "root";
            String password = "juan0911";
            Connection conn = DriverManager.getConnection(url, user, password);

            //获取执行平台
            Statement statement = conn.createStatement();
            String sql = "Delete FROM `sort` where `sid` = 6";
            int num = statement.executeUpdate(sql); // 放回的结果是影响的行数
            System.out.println(num);

            //处理结果集
           // String sql = "select * from `fine`";
           /* ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()){
                int fid = resultSet.getInt("fid");
                double money = resultSet.getDouble("money");
                String userName = resultSet.getString("username");
                System.out.println("id为：" + fid + "金钱为：" + money + "用户名为：" + userName);
            }*/


            //关闭资源
            //resultSet.close();
            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
