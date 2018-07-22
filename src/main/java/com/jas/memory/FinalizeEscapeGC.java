package com.jas.memory;

public class FinalizeEscapeGC {
    private static FinalizeEscapeGC finalizeEscapeGC1;
    public void isAlive(){
        System.out.println("我还ok");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("正在执行finalize方法，这是最后一次自救机会，并且只能执行一次");
        finalizeEscapeGC1 = this;
    }

    public static void main(String[] args) throws InterruptedException {
        FinalizeEscapeGC finalizeEscapeGC = new FinalizeEscapeGC();
        System.out.println(finalizeEscapeGC);
        finalizeEscapeGC = null;
        System.gc();
        Thread.sleep(5000);
        if (finalizeEscapeGC1 != null){
            System.out.println(finalizeEscapeGC1);
            finalizeEscapeGC1.isAlive();
        }else {
            System.out.println("我已经死了");
        }

        finalizeEscapeGC1 = null;
        System.gc();
        Thread.sleep(5000);
        if (finalizeEscapeGC1 != null){
            finalizeEscapeGC1.isAlive();
        }else {
            System.out.println("我已经死了");
        }

    }
}
