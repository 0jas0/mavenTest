package com.jas.test;

public class A {
    public int a;

    A(int i) {
        this.a = i;
    }

    public static void modify(A obj) {
        obj = new A(obj.a + 1);
    }

    public static void main(String[] args) {
        A obj = new A(5);
        modify(obj);
        System.out.println(obj.a);
    }
}