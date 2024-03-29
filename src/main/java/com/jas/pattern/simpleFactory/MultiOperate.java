package com.jas.pattern.simpleFactory;

import java.math.BigDecimal;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class MultiOperate extends Operate{
    @Override
    public double calculate() {
        BigDecimal num1 = new BigDecimal(getNum1());
        BigDecimal num2 = new BigDecimal(getNum2());
        BigDecimal res = num1.multiply(num2);
        return res.doubleValue();
    }
}
