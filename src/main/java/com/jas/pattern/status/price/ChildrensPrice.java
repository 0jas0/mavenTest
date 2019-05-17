package com.jas.pattern.status.price;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class ChildrensPrice extends Price{
    @Override
    public double getCharge(int days) {
        double res = 1.5;
        if (days > 3){
            res += (days - 3) * 1.5;
        }
        return res;
    }
}
