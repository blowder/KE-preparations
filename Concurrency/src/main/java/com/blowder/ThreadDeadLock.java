package com.blowder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDeadLock {
    public static class LockReason {
        Lock one = new ReentrantLock();
        Lock two = new ReentrantLock();

        public void oneFirstMethod() {
            try {
                one.lock();
                System.out.println(Thread.currentThread().getName() + " aquire first lock");
                two.lock();
                System.out.println(Thread.currentThread().getName() + " aquire second lock");
                Thread.sleep(10);
            } catch (InterruptedException e) {
				//do nothing
			} finally {
                one.unlock();
                two.unlock();
            }
        }

        public void twoFirstMethod() {
            try {
                two.lock();
                System.out.println(Thread.currentThread().getName() + " aquire second lock");
                one.lock();
                System.out.println(Thread.currentThread().getName() + " aquire first lock");
                Thread.sleep(10);
            } catch (InterruptedException e) {
				//do nothing
			} finally {
                two.unlock();
                one.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(8);
        LockReason deadlocker = new LockReason();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                service.submit(() -> deadlocker.oneFirstMethod());
            } else {
                service.submit(() -> deadlocker.twoFirstMethod());
            }
        }
        service.shutdown();
        
    }
}