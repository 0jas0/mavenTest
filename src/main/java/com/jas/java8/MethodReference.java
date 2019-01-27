package com.jas.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class MethodReference {
    public static MethodReference create(Supplier<MethodReference> supplier){
        return supplier.get();
    }

    public void follow(MethodReference reference){
        System.out.println("follow: " + reference);
    }

    public static void repair(MethodReference reference){
        System.out.println("repair: " + reference);
    }

    public static void main(String[] args) {
        // 构造器引用 Class::new
        MethodReference methodReference = MethodReference.create(MethodReference::new);
        MethodReference methodReference2 = MethodReference.create(MethodReference::new);
        MethodReference methodReference3 = MethodReference.create(MethodReference::new);

        System.out.println("-----------华丽的分割线-------------");
        List<MethodReference> lists = Arrays.asList(methodReference, methodReference2, methodReference3);
        // 静态方法引用 Class::static_method
        lists.forEach(MethodReference::repair);

        System.out.println("-----------华丽的分割线-------------");
        // 普通方法的引用 instance::method
        lists.forEach(methodReference::follow);

    }
}
