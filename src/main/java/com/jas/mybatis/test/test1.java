package com.jas.mybatis.test;

import com.alibaba.fastjson.JSONObject;
import com.jas.mybatis.bean.StudentPO;
import com.jas.mybatis.dao.IStudentDAO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(inputStream);
        //sqlSessionManager.startManagedSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            IStudentDAO studentDAO = sqlSession.getMapper(IStudentDAO.class);
            //IStudentDAO studentDAO = sqlSessionManager.getMapper(IStudentDAO.class);
            List<StudentPO> productList = studentDAO.selectByCourseId(10);
            System.out.println(JSONObject.toJSONString(productList));
        } finally {
            //sqlSessionManager.close();
            sqlSession.close();
        }

    }
}
