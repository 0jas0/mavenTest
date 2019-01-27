package com.jas.java8;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class DefaultMethod {

    interface vehicle{
        default void print(){
            System.out.println("独轮车");
        }

        static void blowHorn(){
            System.out.println("吧啦吧啦");
        }
    }

    interface vehicle4{
        default void print(){
            System.out.println("4轮车");
        }
    }

    static class Car implements vehicle, vehicle4{

        @Override
        public void print() {
            vehicle.super.print();
            vehicle.blowHorn();
            vehicle4.super.print();
        }
    }
    public static void main(String[] args) {
        Car car = new Car();
        car.print();
    }
}
