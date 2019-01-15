package com.jas.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class UnsafeTest {
    public static Unsafe unsafe;

    public static long stateOffset;

    public volatile int a;

    public volatile long  num = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
            // 获取 num 在 UnsafeTest 中的偏移量
            stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("num"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnsafeTest unsafeTest = new UnsafeTest();
        System.out.println( "偏移量为：" + unsafeTest.stateOffset);
        System.out.println("之前的值为：" + unsafeTest.num);
        unsafe.compareAndSwapLong(unsafeTest, stateOffset, unsafeTest.num, 2L);
        System.out.println("修改之后的值为：" + unsafeTest.num);

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();

        LongAdder adder = new LongAdder();
        adder.add(100L);
        adder.increment();
        System.out.println(adder.intValue());

    }

}
