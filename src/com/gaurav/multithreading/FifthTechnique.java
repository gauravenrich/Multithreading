package com.gaurav.multithreading;

import java.util.concurrent.TimeUnit;

public class FifthTechnique {
    public static void main(String[] args) {
        System.out.println("Main Thread starts here -----");
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=10;i>=0;i--){
                    System.out.println("<1> TICK TICK -- " + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        System.out.println("Main Thread ends here -----");

    }
}
