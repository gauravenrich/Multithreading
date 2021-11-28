package com.gaurav.multithreading;

import java.util.concurrent.TimeUnit;

public class SecondTechnique {
    public static void main(String[] args) {
        System.out.println("Main Thread starts here -----");
        new SecondTask().start();
        Thread t = new SecondTask();
        t.start();
        System.out.println("Main Thread ends here -----");

    }
}

class SecondTask extends Thread {
    private final int id;
    private static int count = 0;

    @Override
    public void run() {
        for (int i = 10; i >= 0; i--) {
            System.out.println("<" + id + "> TICK TICK -- " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SecondTask() {
        this.id = ++count;
    }

}

