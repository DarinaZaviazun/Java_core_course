package main.part4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String s = reader.readLine();
                if (s.equals("stop")) break;
                switch (s.toLowerCase()) {
                    case "cyrl":
                        findCyrl(s);
                        break;
                    case "latn":
                        findLatn(s);
                        break;
                    default:
                        System.out.println(s + ": Incorrect input");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void findLatn(String s1) {
        String s = Part1.readFile("part6.txt");
        StringBuilder sb = new StringBuilder(s1 + ": ");
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            sb.append(matcher.group()).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void findCyrl(String s1) {
        String s = Part1.readFile("part6.txt");
        StringBuilder sb = new StringBuilder(s1 + ": ");
        Pattern pattern = Pattern.compile("\\b[а-яА-ЯЁёЇїІіЄє]+\\b");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            sb.append(matcher.group()).append(" ");
        }
        System.out.println(sb.toString());
    }
}
