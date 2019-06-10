package com.jas.test;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class SubClass extends SuperClass{
    private double var3;
    public SubClass(int var1, String var2, double var3) {
        // 子类的构造函数都会先调用父类的构造函数
        super(var1, var2);
        this.var3 = var3;
    }
}
