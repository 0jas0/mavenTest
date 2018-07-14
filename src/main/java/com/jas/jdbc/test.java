package com.jas.jdbc;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class test {
    @Test
    public void arrayHandler(){
        Connection con = JdbcUtil.getCon();

        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from fine";
        try {
            Object[] query = queryRunner.query(con, sql, new ArrayHandler());
            System.out.println(Arrays.toString(query));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void arrayListHandler() {
        Connection con = JdbcUtil.getCon();

        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from fine";
        try {
            List<Object[]> query = queryRunner.query(con, sql, new ArrayListHandler());
            for (Object[] objects : query) {
                System.out.println(Arrays.toString(objects));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    //todo *****
    public void beanHandler(){
        Connection connection = JdbcUtil.getCon();

        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from fine where `fid` = ?";
        Object[] param = {1};
        try {
            FineDO fine = queryRunner.query(connection, sql, new BeanHandler<FineDO>(FineDO.class), param);
            System.out.println(fine);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    //todo *****
    public void beanListHandler(){
        Connection connection = JdbcUtil.getCon();

        QueryRunner queryRunner = new QueryRunner();
        String sql = "select fid,money,username from fine";
        try {
            List<FineDO> fineDOList = queryRunner.query(connection, sql, new BeanListHandler<FineDO>(FineDO.class));
            for (FineDO fineDO : fineDOList){
                System.out.println(fineDO);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    //todo *****
    public void columnListHandler(){
        Connection connection = JdbcUtil.getCon();

        QueryRunner queryRunner = new QueryRunner();
        String sql = "select username from fine where fid > ?";
        Object[] param = {3};
        try {
            List<String> userNames = queryRunner.query(connection, sql, new ColumnListHandler<String>(), param);
            for (String username : userNames){
                System.out.println(username);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    //todo *****
    public void scalarHandler(){
        Connection connection = JdbcUtil.getCon();

        QueryRunner queryRunner = new QueryRunner();
        String sql = "select count(*) from fine where fid > ?";
        Object[] param = {3};
        try {
            Long num = queryRunner.query(connection, sql, new ScalarHandler<Long>(), param);
            System.out.println(num);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void scalarHandler1() throws PropertyVetoException {
        QueryRunner queryRunner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select count(*) from fine where fid > ?";
        Object[] param = {3};
        try {
            Long num = queryRunner.query(sql, new ScalarHandler<Long>(), param);
            System.out.println(num);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
