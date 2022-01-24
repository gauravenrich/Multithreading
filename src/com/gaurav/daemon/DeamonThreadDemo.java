package com.gaurav.daemon;

public class DeamonThreadDemo extends Thread {
    public DeamonThreadDemo(String name){
        super(name);
    }
    @Override
    public void run() {
        // Checking whether the thread is Daemon or not
        if(Thread.currentThread().isDaemon())
        {
            System.out.println(getName() + " is Daemon thread");
        }
        else
        {
            System.out.println(getName() + " is User thread");
        }
    }

    public static void main(String[] args) {
        DeamonThreadDemo t1 = new DeamonThreadDemo("t1");
        DeamonThreadDemo t2 = new DeamonThreadDemo("t2");
        DeamonThreadDemo t3 = new DeamonThreadDemo("t3");
        // Setting user thread t1 to Daemon
        t1.setDaemon(true);
        // starting first 2 threads
        t1.start();
        t2.start();
        // Setting user thread t3 to Daemon
        t3.setDaemon(true);
        t3.start();
    }
}
