package com.blowder;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadLock {
    static Lock reentrantLock = new ReentrantLock();
    //kind of not reenttrant lock
    static Semaphore lock = new Semaphore(1);

    public static void main(String[] args) {
        System.out.println(recurciveReentrant(10));
    }

    static int recurcive(int number) {
        try {
            // causes deadlock on second enclosion
            lock.acquire();
            if (number <= 1) {
                return number;
            }
            return number * recurcive(number - 1);
        } catch (InterruptedException e) {
            return -1;
        } finally {
            lock.release();
        }
    }

    static int recurciveReentrant(int number) {
        try {
            reentrantLock.lock();
            if (number <= 1) {
                return number;
            }
            return number * recurciveReentrant(number - 1);
        } finally {
            reentrantLock.unlock();
        }
    }
}
