package com.jas.pattern.status.price;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public abstract class Price {
    public abstract double getCharge(int days);
    public int getFrequentRenterPoints(int days){
        return 1;
    }
}
