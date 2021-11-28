package com.gaurav.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorUsingFixedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        for(int i=0;i<6;i++){
            executorService.execute(new LoopTaskA());
        }
        executorService.shutdown();
    }
}
