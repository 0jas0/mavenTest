package com.jas.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/11/7.
 */
public class InvocationHandlerImpl<T> implements InvocationHandler{
    private Object targe;
    public InvocationHandlerImpl() {
    }
    public InvocationHandlerImpl(Object targe) {
        this.targe = targe;
    }

    public T getInstance(){
        return  (T)Proxy.newProxyInstance(targe.getClass().getClassLoader(), targe.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("getName".equals(method.getName())){
            System.out.printf("---------------方法执行之前---------------");
            Object invoke = method.invoke(targe, args);
            if (invoke instanceof  String){
                System.out.println(invoke.toString());
            }
            System.out.printf("---------------方法执行之后---------------");
            return invoke;
        }else {
            return method.invoke(targe,args);
        }
    }
}
