package com.jas.concurrent;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/12/8.
 */
public class CyclicBarrierTest {
    private static class Runner implements Runnable{
        private CyclicBarrier cyclicBarrier;
        private String        name;

        public Runner(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name+"准备好了");
                Thread.sleep(1000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name+"gogogo");
        }
    }

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        CyclicBarrierTest.Runner runner1 = new Runner(cyclicBarrier,"博尔特");
        CyclicBarrierTest.Runner runner2 = new Runner(cyclicBarrier,"苏炳添");
        CyclicBarrierTest.Runner runner3 = new Runner(cyclicBarrier,"李长春");
        CyclicBarrierTest.Runner runner4 = new Runner(cyclicBarrier,"加加林");
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.submit(runner1);
        threadPool.execute(runner2);
        threadPool.execute(runner3);
        threadPool.submit(runner4);
        threadPool.shutdown();
    }
}
