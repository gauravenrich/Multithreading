package com.gaurav.oddeven;

import java.util.concurrent.Semaphore;

class OddEven{
    Semaphore oddSem;
    Semaphore evenSem;
    int n;
    public OddEven(int n){
        oddSem=new Semaphore(1);
        evenSem=new Semaphore(0);
        this.n=n;
    }
    public void printOdd(){
        for(int i=1;i<n;i=i+2){
            try {
                oddSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            evenSem.release();
        }
    }

    public void printEven(){
        for(int i=2;i<n;i=i+2){
            try {
                evenSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            oddSem.release();
        }
    }
}

class OddEvenThread extends Thread{
    String method;
    OddEven oddEven;
    public OddEvenThread(String method,OddEven oddEven){
        this.method=method;
        this.oddEven=oddEven;
    }

    @Override
    public void run() {
        if("Odd".equals(method)){
            oddEven.printOdd();
        }
        if("Even".equals(method)){
            oddEven.printEven();
        }
    }
}
public class OddEvenDemo {
    public static void main(String[] args) throws InterruptedException {
        OddEven oddEven=new OddEven(50);
        OddEvenThread t1=new OddEvenThread("Odd",oddEven);
        OddEvenThread t2=new OddEvenThread("Even",oddEven);
        t2.start();
        t1.join();
    }
}
