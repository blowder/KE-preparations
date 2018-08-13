package com.blowder;

import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;

class AccumulatorTest {
    public static void main(String[] args) {
        DoubleAccumulator acc = new DoubleAccumulator((a, b) -> a * b, 1);
        for (int i = 1; i <= 10; i++) {
            acc.accumulate(i);
        }
        System.out.println("Mutiply of range(1-10) is " + acc.get());

        DoubleAdder adder = new DoubleAdder();
        for (int i = 1; i <= 10; i++) {
            adder.add(i);
        }
        System.out.println("Summ of range(1-10) is " + adder.doubleValue());
    }
}