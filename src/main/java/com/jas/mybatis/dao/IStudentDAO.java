package com.jas.mybatis.dao;

import com.jas.mybatis.bean.StudentPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStudentDAO {

    List<StudentPO> selectByCourseId(@Param("courseId") Integer courseId);
}
