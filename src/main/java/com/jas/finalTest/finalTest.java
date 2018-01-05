package com.jas.finalTest;

/**
 * Created by Administrator on 2017/12/17.
 */
public class finalTest {
    private final person p;

    public finalTest(person p) {
        this.p = p;
    }

    public person getP() {
        return p;
    }

    private static class person{
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "person{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "finalTest{" +
                "p=" + p +
                '}';
    }

    public static void main(String[] args) {
        finalTest.person person = new finalTest.person();
        person.setName("aaa");
        person.setAge("11");
        finalTest finalTest = new finalTest(person);
        System.out.println(finalTest);
    }
}
