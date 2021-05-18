package main.part3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) {
        String input1 = Util.getInput("main.part2.txt");
        System.out.println(input1);
        String s = convert(input1);
        System.out.println(s);
    }

    public static String convert(String input) {
        String[] bezPerenosa = input.split(System.lineSeparator());
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯіїєІЇЄёЁ]+");
        String min = findMin(bezPerenosa, pattern);
        String max = findMax(bezPerenosa, pattern);
        return min + System.lineSeparator() + max;
    }

    public static String findMin(String[] stroki, Pattern pattern){
        int min = 100;
        StringBuilder minLength = new StringBuilder("Min: ");
        for (String a : stroki) {
            Matcher matcher = pattern.matcher(a);
            while (matcher.find()) {
                String group = matcher.group();
                if (group.length() < min) {
                    min = group.length();
                    minLength = new StringBuilder("Min: ").append(group).append(", ");
                }
                else if (group.length() == min && !minLength.toString().contains(group)) {
                        minLength.append(group).append(", ");
                }
            }
        }
        return minLength.delete(minLength.length() - 2, minLength.length()).toString();
    }

    public static String findMax(String[] stroki, Pattern pattern){
        int max = 0;
        StringBuilder maxLength = new StringBuilder("Max: ");
        for (String a : stroki) {
            Matcher matcher = pattern.matcher(a);
            while (matcher.find()) {
                String group = matcher.group();
                if (group.length() > max) {
                    max = group.length();
                    maxLength = new StringBuilder("Max: ").append(group).append(", ");
                }
                else if (group.length() == max && !maxLength.toString().contains(group))
                        maxLength.append(group).append(", ");
            }
        }
        return maxLength.delete(maxLength.length() - 2, maxLength.length()).toString();
    }
}

