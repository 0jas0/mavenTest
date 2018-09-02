package com.jas.proxy2;


public class User {
    public User() {
        System.out.println("user的构造方法");
    }

    public void introduce() throws InterruptedException {
        System.out.println("自我介绍");
        Thread.sleep(500);
    }

    public static void main(String[] args) throws InterruptedException {
        MethodInterceportImpl<User> proxy = new MethodInterceportImpl<>();
        User user = proxy.getInstance(User.class);
        user.introduce();
    }
}
