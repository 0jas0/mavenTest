package com.jas.pattern.status.price;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class NewReleasePrice extends Price{
    @Override
    public double getCharge(int days) {
        return days*3;
    }

    @Override
    public int getFrequentRenterPoints(int days) {
        int point = 1;
        if (days > 1){
            point = 2;
        }
        return point;
    }
}
