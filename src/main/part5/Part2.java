package main.part5;

import java.io.*;

public class Part2 {
    public static void main(String[] args) {
        InputStream in = System.in;
        System.setIn(new FakeInput());
        Thread thread = new Thread() {
            @Override
            public void run() {
                Spam.main(null);
            }
        };
        thread.start();

        try {
            thread.join();
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
        Thread.currentThread().interrupt();
        System.setIn(in);
        System.out.println(Thread.activeCount());
    }

    private static class FakeInput extends InputStream {

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            byte[] bytes = System.lineSeparator().getBytes();
            return super.read(bytes, 0, bytes.length);
        }

        @Override
        public int read() throws IOException {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
            return '\n';
        }
    }
}
