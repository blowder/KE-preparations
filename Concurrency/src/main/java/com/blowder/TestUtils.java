package com.blowder;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class TestUtils {
    private TestUtils() {
    }

    private static String format(Date date) {
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }

    private static String rightPad(String value, int size) {
        if (value.length() >= size) {
            return value;
        }
        int padSize = size - value.length();
        String result = value;
        for (int i = 0; i < padSize; i++) {
            result += " ";
        }
        return result;
    }

    public static void log(String message) {
        String head = format(new Date()) + " - " + rightPad(Thread.currentThread().getName(), 10);
        System.out.println(head + " " + message);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // do nothing
            e.printStackTrace();
        }
    }

}