package main.part6.part6;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part63 {
    public static void duplicatesTask(String task) {
        List<String> words = new ArrayList<>();
        List<String> dupl = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(task);
        while (matcher.find()) words.add(matcher.group());
        for (int a = 0; a < words.size(); a++){
            for (int b = a + 1; b < words.size(); b++){
                if (words.get(a).equals(words.get(b))) {
                    dupl.add(words.get(a));
                    break;
                }
            }
        }
        dupl.stream().limit(3)
                .forEach(a -> System.out.println(new StringBuilder(a).reverse().toString().toUpperCase(Locale.ROOT)));
    }
}
