package com.jas.FutureModel;

/**
 * Created by Administrator on 2017/12/5.
 */
public class FutureModel{

    public Data request(final String str) {
        final FutureData futureData = new FutureData();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(str);
                futureData.setRealData(realData);
            }
        }, "t1");
        t1.start();
        return futureData;
    }
}
