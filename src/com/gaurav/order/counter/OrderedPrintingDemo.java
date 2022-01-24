package com.gaurav.order.counter;

class OrderPrinting{
    int count;
    public OrderPrinting(){
        count=1;
    }
    public void printFirst() throws InterruptedException{
        synchronized (this){
            System.out.println("First");
            count++;
            this.notifyAll();
        }
    }
    public void printSecond() throws InterruptedException{
        synchronized (this){
            while (count!=2){
                this.wait();
            }
            System.out.println("Second");
            count++;
            this.notifyAll();
        }
    }
    public void printThird() throws InterruptedException{
        synchronized (this){
            while (count!=3){
                this.wait();
            }
            System.out.println("Third");
            count++;
            this.notifyAll();
        }
    }

}
class OrderPrintingThread extends Thread {
    OrderPrinting orderPrinting;
    String method;
    public OrderPrintingThread(OrderPrinting orderPrinting,String method){
        this.orderPrinting=orderPrinting;
        this.method=method;
    }
    @Override
    public void run() {
        if("First".equals(method)){
            try {
                orderPrinting.printFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if("Second".equals(method)){
            try {
                orderPrinting.printSecond();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if("Third".equals(method)){
            try {
                orderPrinting.printThird();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class OrderedPrintingDemo {
    public static void main(String[] args) {
        OrderPrinting obj = new OrderPrinting();
        OrderPrintingThread t1=new OrderPrintingThread(obj,"First");
        OrderPrintingThread t2=new OrderPrintingThread(obj,"Second");
        OrderPrintingThread t3=new OrderPrintingThread(obj,"Third");
        t2.start();
        t1.start();
        t3.start();
    }
}
