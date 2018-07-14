package com.jas.FutureModel;

/**
 * Created by Administrator on 2017/12/5.
 */
public class RealData implements Data{
    private String data;

    public RealData(String data) {
        this.data = data;
    }

    @Override
    public String getRequest() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "返回请求参数";
    }
}
