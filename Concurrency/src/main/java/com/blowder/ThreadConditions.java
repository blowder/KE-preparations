package com.blowder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadConditions {
    public static class ThreadCtx {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<Integer> tasks = new ArrayList<>();

        public void produce() {
            try {
                lock.lock();
                for (int i = 0; i < 3; i++) {
                    tasks.add(i);
                }
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " tasks were produced");
                condition.signalAll();
            } catch (InterruptedException e) {
                // do nothing
            } finally {
                lock.unlock();
            }
        }

        public void consume() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " waits for tasks");
                condition.await();
                for (Integer task : tasks) {
                    System.out.println(Thread.currentThread().getName() + " executing " + task);
                }
            } catch (InterruptedException e) {
                // do nothing
            } finally {
                lock.unlock();
            }
        }

    }

    public static class Producer implements Runnable {
        private ThreadCtx ctx;

        public Producer(ThreadCtx ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            ctx.produce();
        }
    }
    public static class Consumer implements Runnable {
        private ThreadCtx ctx;

        public Consumer(ThreadCtx ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            ctx.consume();
        }
    }
    public static void main(String[] args) {
        ThreadCtx ctx = new ThreadCtx();
        //producing of new tasks executing within mutex so consumer thread
        //shoul be executed first
        new Thread(new Consumer(ctx)).start();
        new Thread(new Consumer(ctx)).start();
        new Thread(new Producer(ctx)).start();
    }
}