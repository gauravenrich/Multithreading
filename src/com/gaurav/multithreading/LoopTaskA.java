package com.gaurav.multithreading;

import java.util.concurrent.TimeUnit;

public class LoopTaskA implements Runnable{
    private final int id;
    private static int count = 0;

    @Override
    public void run() {
        System.out.println("##### Task id" + id + "> Starting -- ");
        for (int i = 10; i >= 0; i--) {
            System.out.println("<" + id + "> TICK TICK -- " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("##### Task id" + id + "> Ending -- ");
    }

    public LoopTaskA() {
        this.id = ++count;
    }
}
