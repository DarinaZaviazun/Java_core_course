package main.part4;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class Part2 {

    public static void main(String[] args) {
        Part2 part = new Part2();
        part.writeNumbers();
        part.sortNumbers();
    }

    public void writeNumbers(){
        StringBuilder sb = new StringBuilder("");
        for (int a = 0; a < 10; a++){
            sb.append(new SecureRandom().nextInt(51)).append(" ");
        }
        try (FileWriter fileWriter = new FileWriter("main.part2.txt")){
            fileWriter.write(sb.toString().trim());
            System.out.println("input ==> " + sb.toString().trim());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private void sortNumbers() {
        StringBuilder sb = new StringBuilder("");
        String fromFile = Part1.readFile("main.part2.txt");
        int[] mass = new int[10];
        for (int a = 0; a < 10; a++){
            mass[a] = Integer.parseInt(fromFile.split(" ")[a]);
        }
        for (int i = 0; i < 10; i++){
            for (int j = 1; j < 10; j++){
                if (mass[j] < mass[j - 1]){
                    int c = mass[j - 1];
                    mass[j - 1] = mass[j];
                    mass[j] = c;
                }
            }
        }
        for (int a = 0; a < 10; a++)
            sb.append(mass[a]).append(" ");

        try (FileWriter fileWriter = new FileWriter("part2_sorted.txt")){
            fileWriter.write(sb.toString().trim());
            System.out.println("output ==> " + sb.toString().trim());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}

