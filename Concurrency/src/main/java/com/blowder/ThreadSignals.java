package com.blowder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * THREAD SIGNALS
 */
public class ThreadSignals {

    private static class ThreadContext {
        private List<String> tasks = new ArrayList<>();

        public synchronized void addTask(String task) {
            this.tasks.add(task);
        }

        public synchronized List<String> getTasks() {
            try {
                System.out.println(Thread.currentThread().getName() + "-  Wait for tasks");
                this.wait();
            } catch (InterruptedException e) {
                // do nothing
            }
            return new ArrayList<>(tasks);
        }

        public synchronized void beginProcessing() {
            System.out.println(Thread.currentThread().getName() + "-  Start processing");
            this.notifyAll();
        }
    }

    private static class Producer implements Runnable {
        private ThreadContext ctx;

        public Producer(ThreadContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            for (String task : Arrays.asList("a", "b", "c", "d")) {
                this.ctx.addTask(task);
            }
            this.ctx.beginProcessing();
        }
    }

    private static class Consumer implements Runnable {
        private ThreadContext ctx;

        public Consumer(ThreadContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            for (String task : this.ctx.getTasks()) {
                System.out.println(Thread.currentThread().getName() + "-  Executing task = " + task);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadContext ctx = new ThreadContext();
        Producer producer = new Producer(ctx);
        Consumer consumer = new Consumer(ctx);
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(producer).start();
        /*
         * Thread.sleep(500); new Thread(producer).start(); Thread.sleep(500); new
         * Thread(producer).start();
         */
    }

    public static void runJoinedThread() throws InterruptedException{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " - Hello from worker");
            }
        });
        
        thread.start();
        thread.join();
        System.out.println(Thread.currentThread().getName() + " - Hello from main app");
    }
}
