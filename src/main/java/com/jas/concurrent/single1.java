package com.jas.concurrent;

/**
 * Created by Administrator on 2017/12/5.
 */
public class single1 {
    private static class inner{
        private static single1 s= new single1();
    }
    private single1(){}
    public single1 getInstance(){
        return inner.s;
    }
}
