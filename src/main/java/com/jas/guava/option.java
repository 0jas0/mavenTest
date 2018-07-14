package com.jas.guava;

import com.google.common.base.Function;
import com.google.common.base.Optional;

import java.util.Set;

public class option {
    public static void main(String[] args) {
        //如果传入的值为null则直接报空指针异常，可以用来校验空指针异常
        Optional<String> optional = Optional.of("option");
        //返回包含的实例
        String str = optional.get();
        //返回一个不可变的单集合的唯一元素
        Set<String> set = optional.asSet();
        System.out.println(str+"--"+set);
        //返回一个包含引用Optional实例，否则返回absent()
        optional = optional.fromNullable("aaa");
        System.out.println(optional.get());
        //返回实例的哈希码
        System.out.println(optional.hashCode());
        //返回包含的实例否则返回null
        str = optional.orNull();
        System.out.println(str);
        final int i = 0;
        Optional<Integer> transform = optional.transform(new Function<String, Integer>() {
            public Integer apply(String input) {
                return i;
            }
        });
        System.out.println(transform.get());
    }
}
