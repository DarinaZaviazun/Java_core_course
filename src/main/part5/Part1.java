package main.part5;

public class Part1 {

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        Thread2 thread2 = new Thread2();
        Thread thread11 = new Thread(thread2);
        try {
            thread1.join();
            thread11.start();
            thread11.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    static class Thread1 extends Thread {

        @Override
        public void run() {
            for (int a = 0; a < 4; a++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class Thread2 implements Runnable {

        @Override
        public void run() {
            for (int a = 0; a < 4; a++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
