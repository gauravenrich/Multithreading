package com.gaurav.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {
    AtomicInteger atomicInteger;
    public Foo(){
        this.atomicInteger=new AtomicInteger(1);
    }
    public void first() {
        while (atomicInteger.get()!=1){
            // wait
        }
        atomicInteger.getAndIncrement();
        System.out.println("first");
    }
    public void second() {
        while (atomicInteger.get()!=2){
            //wait
        }
        atomicInteger.getAndIncrement();
        System.out.println("second");
    }
    public void third() {
        while (atomicInteger.get()!=3){
            //wait
        }
        atomicInteger.getAndIncrement();
        System.out.println("third");
    }
}
