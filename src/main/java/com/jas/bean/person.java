package com.jas.bean;

/**
 * Created by Administrator on 2017/10/17.
 */
public class person {
    private int age;
    private String color;
    private int sex;
    private double height;

    public person() {
    }

    public person(int age, String color, int sex, double height) {
        this.age = age;
        this.color = color;
        this.sex = sex;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
