package com.jas.pattern.status.price;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class RegularPrice extends Price{
    @Override
    public double getCharge(int days) {
        double res = 2;
        if (days > 2){
            res = (days - 2) * 1.5;
        }
        return res;
    }
}
