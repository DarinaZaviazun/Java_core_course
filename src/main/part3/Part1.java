package main.part3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    int pass = new SecureRandom().nextInt(9999-1001)+1000;

    public static void main(String[] args) {
        String input1 = Util.getInput("main.part1.txt");
        System.out.println(input1);
        String s = convert1(input1);
        System.out.println(s);
        String s2 = convert2(input1);
        System.out.println(s2);
        String s3 = convert3(input1);
        System.out.println(s3);
        String s4 = convert4(input1);
        System.out.println(s4);
    }

    public static String convert1(String input) {
        String[] bezPerenosa = input.split(System.lineSeparator());
        StringBuilder rez = new StringBuilder("");
        for (int a = 1; a < bezPerenosa.length; a++) {
            String[] proba = bezPerenosa[a].split(";");
            rez.append(proba[0]).append(": ").append(proba[2]).append(System.lineSeparator());
        }
        return rez.toString();
    }

    public static String convert2(String input) {
        String[] bezPerenosa = input.split(System.lineSeparator());
        StringBuilder rez = new StringBuilder("");
        for (int a = 1; a < bezPerenosa.length; a++) {
            String[] proba = bezPerenosa[a].split(";");
            String[] name = proba[1].split(" ");
            rez.append(name[1])
                    .append(" ")
                    .append(name[0])
                    .append(" (email: ")
                    .append(proba[2])
                    .append(")")
                    .append(System.lineSeparator());
        }
        return rez.toString();
    }

    public static String convert3(String input) {
        String[] bezPerenosa = input.split(System.lineSeparator());
        StringBuilder s = new StringBuilder("");
        StringBuilder s3 = new StringBuilder("");
        for (int a = 1; a < bezPerenosa.length; a++) {
            String s1 = bezPerenosa[a].split("@")[1];
            if (!s.toString().contains(s1))
                s.append(s1).append(" ");
        }
        String[] domens = s.toString().split(" ");
        for (String domen : domens) {
            Pattern pattern = Pattern.compile(".*@" + domen);
            s3.append(domen).append(" ==> ");
            for (int b = 1; b < bezPerenosa.length; b++) {
                Matcher matcher = pattern.matcher(bezPerenosa[b]);
                if (matcher.matches())
                    s3.append(bezPerenosa[b].split(";")[0]).append(", ");
            }
            s3.deleteCharAt(s3.length() - 1)
                    .deleteCharAt(s3.length() - 1)
                    .append(System.lineSeparator());
        }
        return s3.toString();
    }

    public static String convert4(String input) {
        String[] bezPerenosa = input.split(System.lineSeparator());
        StringBuilder result =new StringBuilder(bezPerenosa[0] + ";Password" + System.lineSeparator());
        for (int a = 1; a < bezPerenosa.length; a++){
            result.append(bezPerenosa[a]).append(";").append(new Part1().pass).append(System.lineSeparator());
        }
        return result.toString();
    }
}

