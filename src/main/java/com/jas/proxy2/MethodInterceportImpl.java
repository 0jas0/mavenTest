package com.jas.proxy2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceportImpl<T> implements MethodInterceptor{

    public T getInstance(Class<T> clazz){
        Enhancer enhancer = new Enhancer();
        // 设置代理的父类
        enhancer.setSuperclass(clazz);
        // 设置回调方法
        enhancer.setCallback(this);
        // 生成代理对象
        // 原理 ：根据传入的代理类的字节码文件，生成一个 子类 继承了 代理类，生产代理类的子类的对象。
        // 在生成子类的时候会调用父类的构造方法，调用子类的方法会先调用父类的方法
        return  (T)enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();
        Object o1 = methodProxy.invokeSuper(o, objects);
        long end = System.currentTimeMillis();
        System.out.println("方法："+method.getName()+",执行的时间"+(end-start)+"ms");
        return o1;
    }
}
