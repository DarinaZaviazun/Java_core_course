package main.part4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        String input = readFile("main.part1.txt");
        String s = convert(input);
        System.out.println(s);
    }

    public static String convert(String input) {
        StringBuffer sb = new StringBuffer("");
        Pattern pattern = Pattern.compile("\\b(?iu)[a-zа-яёєїі]{4,}\\b");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group().substring(2));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    public static String readFile(String fileName) {
        StringBuilder sb = new StringBuilder("");
        try (Scanner scanner = new Scanner(new File(fileName), "cp1251")) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return sb.toString().trim();
    }
}

