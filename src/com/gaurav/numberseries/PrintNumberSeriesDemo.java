package com.gaurav.numberseries;

import java.util.concurrent.Semaphore;

class PrintNumberSeries{
    Semaphore zeroSem;
    Semaphore oddSem;
    Semaphore evenSem;
    int n;
    public PrintNumberSeries(int n){
        zeroSem=new Semaphore(1);
        evenSem=new Semaphore(0);
        oddSem=new Semaphore(0);
        this.n=n;
    }
    public void printZero(){
        for(int i=0;i<n;i++){
            try {
                zeroSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("0");
            if(i%2!=0){
                evenSem.release();
            }else{
                oddSem.release();
            }
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
            zeroSem.release();
        }
    }

    public void printOdd(){
        for(int i=1;i<n;i=i+2){
            try {
                oddSem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            zeroSem.release();
        }
    }
    
}

class PrintNumberSeriesThread extends Thread{
    String method;
    PrintNumberSeries printNumberSeries;
    public PrintNumberSeriesThread(String method,PrintNumberSeries printNumberSeries){
        this.method=method;
        this.printNumberSeries=printNumberSeries;
    }

    @Override
    public void run() {
        if("Zero".equals(method)){
            printNumberSeries.printZero();
        }
        if("Even".equals(method)){
            printNumberSeries.printEven();
        }
        if("Odd".equals(method)){
            printNumberSeries.printOdd();
        }
    }
}

public class PrintNumberSeriesDemo {

    public static void main(String[] args) {
        PrintNumberSeries printNumberSeries=new PrintNumberSeries(10);
        PrintNumberSeriesThread t1=new PrintNumberSeriesThread("Zero",printNumberSeries);
        PrintNumberSeriesThread t2=new PrintNumberSeriesThread("Odd",printNumberSeries);
        PrintNumberSeriesThread t3=new PrintNumberSeriesThread("Even",printNumberSeries);
        t1.start();
        t2.start();
        t3.start();
    }
}
