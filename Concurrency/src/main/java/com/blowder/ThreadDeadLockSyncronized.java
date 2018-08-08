package com.blowder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDeadLockSyncronized {
    public static class First {

        synchronized public void makeFirstStuff(Second second) {
            System.out.println(Thread.currentThread().getName() + " second is registered");
            second.processSecond();
        }

        synchronized public void processFirst() {
            System.out.println(Thread.currentThread().getName() + " first done some stuff");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

    public static class Second {
        synchronized public void makeSecondStuff(First first) {
            System.out.println(Thread.currentThread().getName() + " first is registered");
            first.processFirst();
        }

        synchronized public void processSecond() {
            System.out.println(Thread.currentThread().getName() + " second done some stuff");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

    public static void main(String[] args) {
        First first = new First();
        Second second = new Second();
        ExecutorService service = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                service.submit(() -> first.makeFirstStuff(second));
            } else {
                service.submit(() -> second.makeSecondStuff(first));
            }
        }
        service.shutdown();
    }
}