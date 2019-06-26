package com.jas.pattern.simpleFactory;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class Calculator {
    public double calculate(double num1, double num2, String operateSign){
        Operate operate = OperateFactory.createOperate(operateSign);
        if (operate == null){
            return 0.0;
        }
        operate.setNum1(num1);
        operate.setNum2(num2);
        return operate.calculate();
    }
}
