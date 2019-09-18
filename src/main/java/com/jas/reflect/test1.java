package com.jas.reflect;

import org.apache.ibatis.reflection.MetaClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class test1 {
    enum Sex{
        MAN(1, "男"),
        WOMAN(2, "女");

        Sex(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        private int value;
        private String desc;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
    public static class Person{
        private String name;
        private Sex sex;
        private int age;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Sex getSex() {
            return sex;
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        MetaClass metaClass = MetaClass.forClass(Person.class);
        System.out.println(metaClass.hasGetter("name"));
        System.out.println(metaClass.hasGetter("sex"));
        System.out.println(metaClass.hasGetter("age"));

        Class<Person> personClass = Person.class;
        Field[] declaredField = personClass.getDeclaredFields();
        for (Field field : declaredField){
            System.out.println("字段名称："+ field.getName());
        }

        /*PUBLIC: 1
        PRIVATE: 2
        PROTECTED: 4
        STATIC: 8
        FINAL: 16
        SYNCHRONIZED: 32
        VOLATILE: 64
        TRANSIENT: 128
        NATIVE: 256
        INTERFACE: 512
        ABSTRACT: 1024
        STRICT: 2048*/
        int modifiers = personClass.getModifiers();
        System.out.println("获取访问权限的等级" + modifiers);

    }
}
