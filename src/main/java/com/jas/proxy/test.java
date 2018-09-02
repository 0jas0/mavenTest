package com.jas.proxy;

/**
 * Created by Administrator on 2017/11/7.
 */
public class test {
    public static void main(String[] args) {
        final UserService userService = new UserServiceImpl();
        InvocationHandlerImpl<UserService> invocationHandler = new InvocationHandlerImpl<>(userService);
        UserService userService1 = invocationHandler.getInstance();
        userService1.getName();
    }
}
