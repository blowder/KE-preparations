package com.blowder;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ThreadFlowControl {
    public static class FinalCountdown {
        CountDownLatch latch = new CountDownLatch(5);

        public void startForCountdown() {
            for (int i = 5; i > 0; i--) {
                try {
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + " counter is " + i);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
        }

        public void waitForCountDown() {
            try {
                latch.await();
                System.out.println(Thread.currentThread().getName() + " executed after countdown");
            } catch (InterruptedException e) {
                // od nothing
            }
        }
    }

    public static class MyBarier {
        CyclicBarrier barier = new CyclicBarrier(5);

        public void process() {
            System.out.println(Thread.currentThread().getName() + " am next to barier");
            try {
                barier.await();
                Thread.sleep(10);
            } catch (BrokenBarrierException | InterruptedException e) {
                // do nothing
            }
            System.out.println(Thread.currentThread().getName() + " am proccessing my task");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        countdownLatchTest();
        Thread.sleep(1000);
        cyclicBarierTest();
    }

    private static void cyclicBarierTest() {
        MyBarier barier = new MyBarier();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> barier.process()).start();
        }
    }

    private static void countdownLatchTest() {
        FinalCountdown counter = new FinalCountdown();
        // starting slaves
        new Thread(() -> counter.waitForCountDown()).start();
        new Thread(() -> counter.waitForCountDown()).start();
        new Thread(() -> counter.waitForCountDown()).start();
        new Thread(() -> counter.waitForCountDown()).start();
        new Thread(() -> counter.waitForCountDown()).start();
        // starting counting master
        new Thread(() -> counter.startForCountdown()).start();
    }
}