package com.blowder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ForkJoinTest {
    public static class Summator extends RecursiveTask<Integer> {
        private static final long serialVersionUID = 1L;
        private final List<Integer> values;

        public Summator(List<Integer> values) {
            this.values = values;
        }

        @Override
        protected Integer compute() {
            TestUtils.log("Received " + values);
            List<Summator> subTasks = new ArrayList<>();
            if (values.size() > 1) {
                int middle = values.size() / 2;
                subTasks.add(new Summator(values.subList(0, middle)));
                subTasks.add(new Summator(values.subList(middle, values.size())));
            } else {
                return values.size() == 1 ? values.get(0) : 0;
            }

            for (Summator var : subTasks) {
                var.fork();
            }
            Integer result = 0;
            for (Summator var : subTasks) {
                result += var.join();
            }
            return result;
        }

    }

    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer result = new ForkJoinPool().invoke(new Summator(data));

        Integer trueResult = 0;
        for (Integer var : data) {
            trueResult += var;

        }

        System.out.println(result + " " + trueResult);
    }
}