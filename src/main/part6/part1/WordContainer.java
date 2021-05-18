package main.part6.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordContainer {
    TreeSet<Word> set = new TreeSet<>();
    
    public static void main(String[] args) {
        WordContainer wordContainer = new WordContainer();
        TreeSet<Word> set = wordContainer.set;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (reader.ready()) {
                String s = reader.readLine();
                patternMethod(s, set);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (Word a : set)
            System.out.println(a.getContent() + " : " + a.getFrequency());
    }

    static void patternMethod (String s, TreeSet<Word> set){
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            String found = matcher.group();
            if (!found.equals("stop")){
                int count = 0;
                for (Word next : set) {
                    if (next.getContent().equals(found)) {
                        set.remove(next);
                        set.add(new Word(found, next.getFrequency() + 1));
                        count++;
                        break;
                    }
                }
                if (count == 0) set.add(new Word(found, 1));
            }
            else matcher.usePattern(Pattern.compile("stop" + ".+"));
        }
    }
}
