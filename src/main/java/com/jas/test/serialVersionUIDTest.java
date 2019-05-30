package com.jas.test;

import java.io.*;

public class serialVersionUIDTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("路飞",5);
        person.grade = "男";
        //ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("./person")));
        oos.writeObject(person);


        person.grade = "女";
        FileInputStream fileInputStream = new FileInputStream(new File("./person"));
        byte[] bytes = new byte[2*1024];
        int len = 0;
        while ( (len = fileInputStream.read(bytes)) != -1){
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Person person1 = (Person)ois.readObject();
            System.out.println(person1);
        }
    }
}

class Person implements Serializable{
    private static final long serialVersionUID = 123456789L;

    public static String grade;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    private transient String name;

    private int age;

    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                "，grade="+ grade +
                '}';
    }
}
