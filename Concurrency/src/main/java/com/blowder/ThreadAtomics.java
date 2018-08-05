package com.blowder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ThreadAtomics {

    public static class MyAtomicInteger {
        private volatile AtomicInteger value = new AtomicInteger(0);

        public int getValue() {
            return this.value.intValue();
        }

        public void increment() {
            try {
                while (true) {
                    int oldValue = this.value.get();
                    // calculations based on previous value
                    int newValue = oldValue + 1;
                    Thread.sleep(10);
                    if (this.value.compareAndSet(oldValue, newValue)) {
                        System.out.println("Value changed from " + oldValue + " to " + newValue);
                        return;
                    }
                }
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

    public static class Incrementor implements Runnable {
        private MyAtomicInteger value;

        public Incrementor(MyAtomicInteger value) {
            this.value = value;
        }

        @Override
        public void run() {
            this.value.increment();
        }

    }

    public static void main(String[] args) {
        try {
            MyAtomicInteger value = new MyAtomicInteger();
            ExecutorService service = Executors.newFixedThreadPool(8);
            for (int i = 0; i < 10; i++) {
                service.submit(new Incrementor(value));
            }
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
            System.out.println("Value is " + value.getValue());
        } catch (InterruptedException e) {
            // do nothing
        }

    }
}