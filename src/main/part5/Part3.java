package main.part5;

import java.util.concurrent.CountDownLatch;

public class Part3 {

    private int counter;
    private int counter2;
    private int numberOfThreads;
    private int numberOfIterations;
    final Object obj = new Object();

    public Part3(int numberOfThreads, int numberOfIterations) {
        this.numberOfThreads = numberOfThreads;
        this.numberOfIterations = numberOfIterations;
    }

    public static void main(final String[] args) {
        Part3 part3 = new Part3(2, 3);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                part3.compare();
            }
        };
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
        Thread t2 = new Thread() {
            @Override
            public void run() {
                part3.compareSync();
            }
        };
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void compare() {
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        for(int j = 0; j < numberOfThreads; j++) {
             new Thread() {
                @Override
                public void run() {
                    innerMethod(countDownLatch);
                }
            }.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void compareSync() {
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        for(int j = 0; j < numberOfThreads; j++) {
            new Thread() {
                @Override
                public void run() {
                    synchronized (obj) {
                        innerMethod(countDownLatch);
                    }
                }
            }.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void innerMethod(CountDownLatch countDownLatch) {
        for (int i = 0; i < numberOfIterations; i++) {
            System.out.println(counter + " == " + counter2);
            counter++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
            counter2++;
        }
        countDownLatch.countDown();
    }
}
