package com.blowder;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class BlockingQueueTest {
    public static class TaskProducer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public TaskProducer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TestUtils.log("Producing task with id=" + i);
                    queue.put(i);
                    TestUtils.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static class TaskConsumer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public TaskConsumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TestUtils.log("Waiting for task");
                    Integer taskId = queue.take();
                    TestUtils.log("Executing task with id=" + taskId);
                    TestUtils.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(1);
        TaskProducer producer = new TaskProducer(queue);
        TaskConsumer consumer = new TaskConsumer(queue);
        new Thread(consumer).start();
        TestUtils.sleep(1000);
        new Thread(producer).start();
    }
}