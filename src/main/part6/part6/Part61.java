package main.part6.part6;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part61 {
    private Part61() {
    }

    public static void frequencyTask(String task) {
        Map<String, Integer> words = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(task);
        while (matcher.find()) {
            String word = matcher.group();
            int count = 0;
            for(Map.Entry<String, Integer> mapa : words.entrySet()){
                if (mapa.getKey().equals(word)) words.replace(mapa.getKey(), words.get(mapa.getKey()) + 1);
                else count++;
            }
            if (count == words.size()) words.put(word, 1);
        }
        words.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(3)
                .sorted((a, b) -> b.getKey().compareTo(a.getKey()))
                .forEach(a -> System.out.println(a.getKey() + " ==> " + a.getValue()));
    }
}
