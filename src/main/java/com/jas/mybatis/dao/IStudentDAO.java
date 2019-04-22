package com.jas.mybatis.dao;

import com.jas.mybatis.bean.StudentPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IStudentDAO {

    List<StudentPO> selectByCourseId(@Param("courseId") Integer courseId);

    @Select("select * from test.student where id = #{id}")
    StudentPO selectById(@Param("id") Integer id);
}
