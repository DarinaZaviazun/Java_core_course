package main.part4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (true){
                String s = reader.readLine();
                if (s.equals("stop")) break;
                determine(s);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void determine(String s) {
        switch (s) {
            case "int":
                printCase("\\b(?<!\\.)\\d+(?!\\.)\\b");
                break;
            case "char":
                printCase("\\b(?ui)[a-zа-яёєїіъ]\\b");
                break;
            case "String":
                printCase("\\b(?ui)[a-zа-яёєїіъ]{2,}\\b");
                break;
            case "double":
                printCase("\\b?[0-9]*\\.[0-9]+\\b");
                break;
            default:
                System.out.println("Incorrect input");
        }
    }

    private static void printCase(String regex){
        String found = Part1.readFile("main.part3.txt");
        StringBuilder sb = new StringBuilder("");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(found);
        while (matcher.find())
            sb.append(matcher.group()).append(" ");
        System.out.println(sb.toString());
    }
}

