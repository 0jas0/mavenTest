package com.jas.pattern;

import com.jas.pattern.status.Rental;

import java.util.List;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class Customer {
    private List<Rental> rentalInfos;
    private String name;

    public double getTotalCharges(){
        double res = 0;
        for (Rental rental : rentalInfos){
            res += rental.getCharge();
        }
        return res;
    }

    public int getTotalPoints(){
        int res = 0;
        for (Rental rental : rentalInfos){
            res += rental.getFrequentRenterPoints();
        }
        return res;
    }
}
