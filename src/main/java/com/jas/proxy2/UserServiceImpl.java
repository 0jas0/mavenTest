package com.jas.proxy2;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserServiceImpl {
    public void delete(){
        System.out.println("删除一个用户");
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("执行方法之前");
                Object invoke = methodProxy.invokeSuper(o, objects);
                System.out.println("执行方法之后");
                return invoke;
            }
        });
        UserServiceImpl userService = (UserServiceImpl) enhancer.create();
        userService.delete();
    }
}
