package com.jas.java8;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class FunctionInterface {
    public static void main(String[] args) {
        BiConsumer<String, String> biConsumer = (str1, str2) ->{
            System.out.println(str1+"&&"+str2);
        };
        biConsumer.accept("test", "test1");

        System.out.println("-----------华丽的分割线-------------");

        Predicate<Integer> predicate = integer -> integer % 2 == 0;
        System.out.println(predicate.test(2));
    }
}
