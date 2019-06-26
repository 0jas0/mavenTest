package com.jas.pattern.simpleFactory;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public abstract class Operate {
    private double num1;
    private double num2;

    public abstract double calculate();

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    protected double getNum1() {
        return num1;
    }

    protected double getNum2() {
        return num2;
    }
}
