package main.part5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Part4 {
    static ArrayList<String> strings = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("main.part4.txt"))) {
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Part4.multiFind();
        Part4.singleFind();
    }

    private static void multiFind() {
        long m = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(strings.size());
        int[] rezults = new int[strings.size()];
        for (int a = 0; a < strings.size(); a++) {
            int finalA = a;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    String[] s = strings.get(finalA).split(" ");
                    int rez = 0;
                    for (String value : s) {
                        if (Integer.parseInt(value) > rez) {
                            rez = Integer.parseInt(value);
                        }
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                            Thread.currentThread().interrupt();
                        }
                    }
                    rezults[finalA] = rez;
                    countDownLatch.countDown();
                }
            };
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.out.println(Arrays.stream(rezults).max().getAsInt());
        System.out.println((int) (System.currentTimeMillis() - m));
    }

    private static void singleFind() {
        long m = System.currentTimeMillis();
        int rez = 0;
        for (String string : strings) {
            String[] s = string.split(" ");
            for (String value : s) {
                if (Integer.parseInt(value) > rez) {
                    rez = Integer.parseInt(value);
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println(rez);
        System.out.println((int) (System.currentTimeMillis() - m));
    }
}
