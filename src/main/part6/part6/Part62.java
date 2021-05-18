package main.part6.part6;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part62 {

    public static void lengthTask(String task) {
        List<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(task);
        while (matcher.find()) {
            String word = matcher.group();
            int count = 0;
            for (String key : words){
                if (key.equals(word)) break;
                else count ++;
            }
            if (count == words.size()) words.add(word);
        }
        words.stream().sorted((a, b) -> b.length() - a.length())
                .limit(3)
                .sorted(String::compareTo)
                .forEach(a -> System.out.println(a + " ==> " + a.length()));
    }
}
