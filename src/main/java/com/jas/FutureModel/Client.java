package com.jas.FutureModel;

/**
 * Created by Administrator on 2017/12/5.
 */
public class Client {
    public static void main(String[] args) {
        FutureModel futureModel = new FutureModel();
        Data data = futureModel.request("请求参数");
        System.out.println(data.getRequest());
    }
}
