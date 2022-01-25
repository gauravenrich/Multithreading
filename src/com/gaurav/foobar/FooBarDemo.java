package com.gaurav.foobar;

/**
 * @author gsinha
 * 25th jan 2022
 * Foo Bar Problem
 */
class FooBar {
    int n;
    boolean flag = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (!flag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Foo");
                flag=!flag;
                this.notifyAll();
            }
        }
    }

    public void bar() {
        for(int i=0;i<n;i++){
            synchronized (this){
                while (flag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Bar");
                flag=!flag;
                this.notifyAll();
            }
        }
    }
}

class FooBarThread extends Thread {
    FooBar fooBar;
    String method;
    public FooBarThread(FooBar fooBar,String method){
        this.fooBar=fooBar;
        this.method=method;
    }

    @Override
    public void run() {
        if("Foo".equals(method)){
            fooBar.foo();
        }
        if("Bar".equals(method)){
            fooBar.bar();
        }
    }
}

public class FooBarDemo {
    public static void main(String[] args) {
        FooBar fooBar=new FooBar(10);
        FooBarThread t1=new FooBarThread(fooBar,"Foo");
        FooBarThread t2=new FooBarThread(fooBar,"Bar");
        t1.start();
        t2.start();
    }
}
