package com.gaurav.number;

import java.util.concurrent.Semaphore;

class NumberSync {
    int n;
    int count = 0;
    Semaphore one;
    Semaphore two;
    Semaphore three;

    public NumberSync(int n) {
        this.n = n;
        one=new Semaphore(1);
        two=new Semaphore(0);
        three=new Semaphore(0);
    }

    public void printOne() throws InterruptedException {
       for(int i=0;i<n;i++){
           one.acquire();
           System.out.println("1");
       }

    }

    public void printTwo() throws InterruptedException {
        synchronized (this) {
            while (count < n) {
                if (count != 1) {
                    this.wait();
                }
                System.out.println("2");
                count++;
                this.notifyAll();
            }
        }
    }

    public void printThree() throws InterruptedException {
        synchronized (this) {
            while (count < n) {
                if (count != 2) {
                    this.wait();
                }
                System.out.println("3");
                count=0;
                this.notifyAll();
            }
        }
    }
}

class NumberSyncThread extends Thread {
    String methodName;
    NumberSync numberSync;

    public NumberSyncThread(String methodName, NumberSync numberSync) {
        this.methodName = methodName;
        this.numberSync = numberSync;
    }

    @Override
    public void run() {
        if ("one".equals(methodName)) {
            try {
                numberSync.printOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if ("two".equals(methodName)) {
            try {
                numberSync.printTwo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if ("three".equals(methodName)) {
            try {
                numberSync.printThree();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class NumberSyncDemo {
    public static void main(String[] args) {
        NumberSync numberSync = new NumberSync(10);
        NumberSyncThread t1 = new NumberSyncThread("one", numberSync);
        NumberSyncThread t2 = new NumberSyncThread("two", numberSync);
        NumberSyncThread t3 = new NumberSyncThread("three", numberSync);
        t1.start();
        t2.start();
        t3.start();
    }
}