package com.gaurav.leetcode;

public class FooTwo {
    boolean first;
    boolean second;
    Object firstObject = new Object();
    Object secondObject = new Object();

    public FooTwo() {
        first = false;
        second = false;
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        synchronized (firstObject){
            printFirst.run();;
            first=true;
            firstObject.notify();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (firstObject){
            while (!first){
                firstObject.wait();
            }
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        synchronized (secondObject){
            printSecond.run();
            second=true;
            secondObject.notify();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (secondObject){
            while (!second){
                secondObject.wait();
            }
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
