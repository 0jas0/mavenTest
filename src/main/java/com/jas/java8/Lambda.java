package com.jas.java8;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class Lambda {

    interface MathOperation{
        int operation(int a, int b);
    }

    interface SayService{
        void sayMessage(String message);
    }

    public int operation(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        System.out.println("10 + 5 = " + lambda.operation(10,5, (int a,int b) -> a+b));
        System.out.println("10 - 5 = " + lambda.operation(10,5, (a, b) -> a-b));
        System.out.println("10 * 5 = " + lambda.operation(10,5, (a, b) -> {return  a*b;}));
        System.out.println("10 / 5 = " + lambda.operation(10,5, (a, b) -> a/b));
        SayService sayService = message -> {
            System.out.println("jas say:" + message);
        };
        sayService.sayMessage("hello world");

        Runnable aNew = Lambda::new;

    }
}
