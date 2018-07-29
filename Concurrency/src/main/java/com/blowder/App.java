package com.blowder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class App {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(() -> {
            System.out.println("Hello World!");
        });
        System.out.println("Wait for result");
        service.shutdown();
    }  
}
