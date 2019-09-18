package com.jas.java8;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class OptionalClass {
    public static void main(String[] args) {
        Optional<Integer> optional = Optional.of(11);
        Predicate<Integer> p1 = x -> x.equals(10);
        Predicate<Integer> p2 = x -> x.equals(12);
        Integer value = optional.filter(p1.or(p2)).orElseGet(()->10);
        System.out.println(value);
    }
}
