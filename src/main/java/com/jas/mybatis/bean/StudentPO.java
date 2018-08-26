package com.jas.mybatis.bean;

public class StudentPO {
    private Integer id;
    private Integer courseId;
    private Integer studentId;
    private Integer ceId;
    private Integer addtime;
    private Integer modtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getModtime() {
        return modtime;
    }

    public void setModtime(Integer modtime) {
        this.modtime = modtime;
    }
}
