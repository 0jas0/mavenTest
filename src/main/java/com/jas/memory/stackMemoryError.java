package com.jas.memory;

/**
 * -Xss160k
 */
public class stackMemoryError {
    private int stackLength = 1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    private void dontStop(){
        while (true){
        }
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        stackMemoryError stackMemoryError = new stackMemoryError();
        //stackMemoryError.stackLeakByThread();
        try {
            stackMemoryError.stackLeak();
        }catch (Throwable throwable){
            System.out.println(stackMemoryError.stackLength);
        }
    }
}
