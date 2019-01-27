package com.jas.mybatis.test;

import com.jas.mybatis.bean.StudentPO;
import com.jas.mybatis.dao.IStudentDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class test2 {
    @Resource
    private ApplicationContext applicationContext;


    @Test
    public void test1(){
        IStudentDAO iStudentDAO = applicationContext.getBean(IStudentDAO.class);
        List<StudentPO> productList = iStudentDAO.selectByCourseId(10);
    }
}
