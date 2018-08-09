package com.blowder;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class ThreadFlowControl {
    public static class FinalCountdown {
        CountDownLatch latch = new CountDownLatch(5);

        public void startForCountdown() {
            for (int i = 5; i > 0; i--) {
                latch.countDown();
                TestUtils.log("Counter is " + i);
                TestUtils.sleep(50);
            }
        }

        public void waitForCountDown() {
            try {
                latch.await();
                TestUtils.sleep(10);
                TestUtils.log("Executed after countdown");
            } catch (InterruptedException e) {
                // od nothing
            }
        }
    }

    public static class MyBarier {
        CyclicBarrier barier = new CyclicBarrier(5);

        public void process() {
            TestUtils.log("Am next to barier");
            try {
                barier.await();
                TestUtils.sleep(10);
            } catch (BrokenBarrierException | InterruptedException e) {
                // do nothing
            }
            TestUtils.log("Am proccessing my task");
        }
    }

    public static class TaskProducerWithSemaphore {
        private Semaphore semaphore = new Semaphore(2);

        public void showTask() {
            try {
                semaphore.acquire();
                TestUtils.sleep(1000);
                TestUtils.log("Hello task executed");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        countdownLatchTest();
        TestUtils.sleep(1000);
        TestUtils.log("-----------------------");
        cyclicBarierTest();
        TestUtils.sleep(1000);
        TestUtils.log("-----------------------");
        semaphoreTest();
        TestUtils.sleep(1000);
        TestUtils.log("-----------------------");
        exchangerTest();
    }

    public static class ExchangerUser {
        private Exchanger<String> exchanger = new Exchanger<String>();

        public void produce() {
            try {
                TestUtils.log("Ready to produce \"Hello\"");
                TestUtils.sleep(1000);
                this.exchanger.exchange("Hello from " + Thread.currentThread().getName() + "!!");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void consume() {
            try {
                TestUtils.log("Ready to consume \"Hello\"");
                String result = this.exchanger.exchange(null);
                TestUtils.log("Received message: \"" + result + "\"");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void exchangerTest() {
        ExchangerUser exchanger = new ExchangerUser();
        new Thread(() -> exchanger.consume()).start();
        new Thread(() -> exchanger.produce()).start();

    }

    private static void semaphoreTest() {
        TaskProducerWithSemaphore tSemaphore = new TaskProducerWithSemaphore();
        for (int i = 0; i < 8; i++) {
            new Thread(() -> tSemaphore.showTask()).start();
        }
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