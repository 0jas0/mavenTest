package com.jas.FutureModel;

/**
 * Created by Administrator on 2017/12/5.
 */
public class FutureData implements Data {
    private boolean isReady = false;
    private RealData realData;
    public synchronized  void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notify();
    }

    @Override
    public synchronized String getRequest() {
        if(!isReady){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getRequest();
    }
}
