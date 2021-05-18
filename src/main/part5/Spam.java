package main.part5;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Spam {
    private Thread[] threads;
    private String[] messages;
    private int[] delays;

    public Spam(final String[] messages, final int[] delays) {
        this.messages = messages;
        this.delays = delays;
    }

    public static void main(final String[] args) {

        String[] mes = new String[]{"aaaa", "bbbb"};
        int[] del = new int[]{250, 500};
        Spam spam = new Spam(mes, del);
        spam.start();
        try {
            while (System.in.read() != '\n');
            spam.stop();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        threads = new Thread[messages.length];
        for (int a = 0; a < threads.length; a++) {
            threads[a] = new Worker(messages[a], delays[a]);
            threads[a].start();
        }
    }

    public void stop() {
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        for (Thread thread : threads){
            thread.interrupt();
            while (thread.getState() != Thread.State.TERMINATED) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
            countDownLatch.countDown();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static class Worker extends Thread {
        String str;
        int del;

        public Worker(String message, int delay) {
            this.str = message;
            this.del = delay;
        }

        @Override
        public void run(){
            while (!isInterrupted()){
                System.out.println(str);
                try {
                    Thread.sleep(del);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
