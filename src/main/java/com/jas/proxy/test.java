package com.jas.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/11/7.
 */
public class test {
    public static void main(String[] args) {
        final UserService userService = new UserServiceImpl();
        UserService userService1 = (UserService)Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(),UserServiceImpl.class.getInterfaces(),new InvocationHandlerImpl(userService));
        userService1.getName();
    }
}
