package main.part5;

import java.io.*;

public class Part5 {
    public static void main(String[] args) {
        writeData();
        try (BufferedReader reader = new BufferedReader(new FileReader("main.part5.txt"))) {
            while (reader.ready())
                System.out.println(reader.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void writeData() {
        try (RandomAccessFile randomAccess = new RandomAccessFile("main.part5.txt", "rw")) {
            for (int a = 0; a < 10; a++) {
                int finalA = a;
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        for (int b = 0; b < 20; b++) {
                            try {
                                randomAccess.write('0' + finalA);
                                Thread.sleep(1);
                            } catch (IOException | InterruptedException e) {
                                System.out.println(e.getMessage());
                                Thread.currentThread().interrupt();
                            }
                        }
                        try {
                            randomAccess.write(System.lineSeparator().getBytes());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                };
                thread.start();
                thread.join();
            }
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}

