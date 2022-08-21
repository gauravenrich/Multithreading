package com.gaurav.barrier;

public class Barrier {
    int count=0;
    int released=0;
    int totalThreads;

    public Barrier(int totalThreads){
        this.totalThreads=totalThreads;
    }

    public synchronized void await() throws InterruptedException {
        count++;
        if(count==totalThreads){
            notifyAll();
            count=0;
        }else {
            wait();
        }
    }

    public static void runTest() throws  InterruptedException{
        Barrier barrier=new Barrier(3);
        Thread t1=new Thread(()->{
            try {
                System.out.println("Thread 1");
                barrier.await();
                System.out.println("Thread 1");
                barrier.await();
                System.out.println("Thread 1");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2=new Thread(()->{
            try {
                System.out.println("Thread 2");
                barrier.await();
                System.out.println("Thread 2");
                barrier.await();
                System.out.println("Thread 2");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3=new Thread(()->{
            try {
                System.out.println("Thread 3");
                barrier.await();
                System.out.println("Thread 3");
                barrier.await();
                System.out.println("Thread 3");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
