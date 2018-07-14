package com.jas.concurrent;

/**
 * Created by Administrator on 2017/12/5.
 */
public class single2 {
    private static single2 s;
    private single2(){}
    public single2 getInstance(){
        if(s==null){
            synchronized (single2.class){
                if(s==null){
                     s = new single2();
                }
            }
        }
        return s;
    }
}
