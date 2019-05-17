package com.jas.pattern.status;

import com.jas.pattern.status.price.Price;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class Movie {
    private String name;
    private Price price;
    public double getCharge(int days){
        return price.getCharge(days);
    }
    public double getFrequentRenterPoints(int days){
        return price.getFrequentRenterPoints(days);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
