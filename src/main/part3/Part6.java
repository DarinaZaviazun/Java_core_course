package main.part3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        String input1 = Util.getInput("part6.txt");
        System.out.println(input1);
        String s = convert(input1);
        System.out.println(s);
    }

    public static String convert(String input) {
        String[] words = input.split("\\p{Space}+");
        for (String word : words) {
            StringBuffer sb = new StringBuffer("");
            int count = 0;
            Pattern pattern = Pattern.compile("\\b" + word + "\\b");
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                count++;
            }
            Matcher matcher1 = pattern.matcher(input);
            if (count > 1) {
                while (matcher1.find()){
                    matcher1.appendReplacement(sb, "_" + word);
                }
            }
            matcher1.appendTail(sb);
            input = sb.toString();
        }
        return input;
    }
}
