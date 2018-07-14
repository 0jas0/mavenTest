package com.jas.jdbc;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class test3 {
    // todo ***
    public static void main(String[] args) {
        Connection con = JdbcUtil.getCon();

        //获取一个执行sql 的对象 queryRunner
        QueryRunner queryRunner = new QueryRunner();

        /*String sql = "insert into `sort`(`sid`,`sname`) VALUES (?,?)";
        Object[] param = new Object[]{10,"琚安生"};*/
        String sql = "update `sort` set `sname` = ? where `sid` = ?";
        Object[] param = new Object[]{"14213qrewrqwe",1};
       /* String sql = "delete from `sort` where `sid` = ? ";
        Object[] param = new Object[]{4};*/
        try {
            int num = queryRunner.update(con, sql, param);
            System.out.println(num);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
