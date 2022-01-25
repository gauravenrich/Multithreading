package com.gaurav.fizzbuzz;

/**
 * @author gsinha
 * 25 jan 2022
 * Multithreaded FizzBuzz program
 */
class FizzBuzz{
    int n;
    int num;
    public FizzBuzz(int n,int num){
        this.n=n;
        this.num=num;
    }

    public synchronized void printFizz() throws InterruptedException {
            while (num <= n) {
            if(num%3==0 && num%5!=0){
                System.out.println("fizz");
                num++;
                this.notifyAll();
            }else {
                this.wait();
            }
        }
    }
    public synchronized void printBuzz() throws InterruptedException {
            while (num <= n) {
                if (num % 3 != 0 && num % 5 == 0) {
                    System.out.println("buzz");
                    num++;
                    this.notifyAll();
                } else {
                    this.wait();
                }
            }
    }
    public synchronized void printFizzBuzz() throws InterruptedException {
        while (num <= n) {
                if (num % 3 == 0 && num % 5 == 0) {
                    System.out.println("Fizzbuzz");
                    num++;
                    this.notifyAll();
                } else {
                    this.wait();
                }
        }
    }
    public synchronized void printNumber() throws InterruptedException {
        while (num <= n) {
                if (num % 3 != 0 && num % 5 != 0) {
                    System.out.println(num);
                    num++;
                    this.notifyAll();
                } else {
                    this.wait();
                }
        }

    }
}
class FizzBuzzThread extends Thread{
    String method;
    FizzBuzz fizzBuzz;
    public FizzBuzzThread(String method,FizzBuzz fizzBuzz){
        this.method=method;
        this.fizzBuzz=fizzBuzz;
    }

    @Override
    public void run() {
        if("Fizz".equals(method)){
            try {
                fizzBuzz.printFizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if("Buzz".equals(method)){
            try {
                fizzBuzz.printBuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if("FizzBuzz".equals(method)){
            try {
                fizzBuzz.printFizzBuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if("Number".equals(method)){
            try {
                fizzBuzz.printNumber();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class MultithreadedFizzBuzz {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz=new FizzBuzz(15,1);
        FizzBuzzThread t1=new FizzBuzzThread("Fizz",fizzBuzz);
        FizzBuzzThread t2=new FizzBuzzThread("Buzz",fizzBuzz);
        FizzBuzzThread t3=new FizzBuzzThread("FizzBuzz",fizzBuzz);
        FizzBuzzThread t4=new FizzBuzzThread("Number",fizzBuzz);
        t3.start();
        t4.start();
        t1.start();
        t2.start();

    }
}
