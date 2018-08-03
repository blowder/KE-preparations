package com.blowder;

import java.util.UUID;

public class ThreadLocalsExample {
    static ThreadLocal<String> local = new ThreadLocal<>();
    static InheritableThreadLocal<String> inherited = new InheritableThreadLocal<>();

    public static class ParameterHolder implements Runnable {

        @Override
        public void run() {
            if (local.get() != null) {
                System.out.println(Thread.currentThread().getName() + " local value is =\"" + local.get()+"\"");
            } else {
                System.err.println(Thread.currentThread().getName() + " local value is absent");
            }
            if (inherited.get() != null) {
                System.out.println(Thread.currentThread().getName() + " inherited value is =\"" + inherited.get()+"\"");
            }else{
                System.err.println(Thread.currentThread().getName() + " inherited value is absent");
            }
        }
    }

    public static void main(String[] args) {
        local.set(Thread.currentThread().getName() + " Hello am " + UUID.randomUUID().toString());
        inherited.set(Thread.currentThread().getName());
        
        new Thread(new ParameterHolder()).start();
    }
}