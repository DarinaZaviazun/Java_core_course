package main.part3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        String input1 = Util.getInput("main.part3.txt");
        System.out.println(input1);
        String s = convert(input1);
        System.out.println(s);
    }

    public static String convert(String input) {
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯіїєІЇЄёЁ]{3,}");
        Matcher matcher = pattern.matcher(input);
        StringBuilder sb = new StringBuilder(input);
        while (matcher.find()){
            char first = matcher.group().charAt(0);
            int index = matcher.start();
            if (Character.isLowerCase(first)){
                sb.setCharAt(index, Character.toUpperCase(first));
            }
            else
                sb.setCharAt(index, Character.toLowerCase(first));
        }
        return sb.toString();
    }
}

