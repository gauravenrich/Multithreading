package com.gaurav.order.countdownlatch;

import java.util.concurrent.CountDownLatch;

class OrderPrinting {
    CountDownLatch latch1;
    CountDownLatch latch2;

    public OrderPrinting() {
        latch1 = new CountDownLatch(1);
        latch2 = new CountDownLatch(1);
    }

    public void printFirst() throws InterruptedException {
        System.out.println("First");
        latch1.countDown();
    }

    public void printSecond() throws InterruptedException {
        latch1.await();
        System.out.println("Second");
        latch2.countDown();
    }

    public void printThird() throws InterruptedException {
        latch2.await();
        System.out.println("Third");
    }

}

class OrderPrintingThread extends Thread {
    String method;
    OrderPrinting orderPrinting;

    public OrderPrintingThread(String method, OrderPrinting orderPrinting) {
        this.method = method;
        this.orderPrinting = orderPrinting;
    }

    @Override
    public void run() {
        if ("First".equals(method)) {
            try {
                orderPrinting.printFirst();
            } catch (InterruptedException e) {

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

public class OrderPrintingLatchDemo {
    public static void main(String[] args) {
        OrderPrinting obj=new OrderPrinting();
        OrderPrintingThread t1=new OrderPrintingThread("First",obj);
        OrderPrintingThread t2=new OrderPrintingThread("Second",obj);
        OrderPrintingThread t3=new OrderPrintingThread("Third",obj);
        t1.start();
        t3.start();
        t2.start();
    }
}
