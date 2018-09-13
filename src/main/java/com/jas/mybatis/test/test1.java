package com.jas.mybatis.test;

import com.alibaba.fastjson.JSONObject;
import com.jas.mybatis.bean.StudentPO;
import com.jas.mybatis.dao.IStudentDAO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(inputStream);
        sqlSessionManager.startManagedSession();
        try {
            IStudentDAO studentDAO = sqlSessionManager.getMapper(IStudentDAO.class);
            List<StudentPO> productList = studentDAO.selectByCourseId(10);
            List<StudentPO> productList1 = studentDAO.selectByCourseId(10);
            System.out.println(JSONObject.toJSONString(productList));
        } finally {
            sqlSessionManager.close();
        }

    }
}
