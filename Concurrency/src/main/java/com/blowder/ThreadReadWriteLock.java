package com.blowder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

public class ThreadReadWriteLock {
    static class TaskDisplay {
        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final List<String> tasks;

        public TaskDisplay(List<String> tasks) {
            this.tasks = new ArrayList<String>(tasks);
        }

        public void display(Consumer<List<String>> taskConsumer) {
            try {
                lock.readLock().lock();
                taskConsumer.accept(tasks);
            } finally {
                lock.readLock().unlock();
            }
        }

        public void addTask(String task) {
            try {
                this.lock.writeLock().lock();
                TestUtils.sleep(10);
                TestUtils.log("Task submited " + task);
                tasks.add(task);
            } finally {
                this.lock.writeLock().unlock();
            }
        }

    }

    public static void main(String[] args) {
        TaskDisplay display = new TaskDisplay(Arrays.asList("one", "two"));
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                final int var = i;
                new Thread(() -> display.addTask("task-" + var)).start();
            }
            if (i % 5 == 0) {
                new Thread(() -> display.display(task -> TestUtils.log("Executing " + task))).start();
            }
        }
    }

}