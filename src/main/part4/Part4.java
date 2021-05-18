package main.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String>{

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        Iterator<String> iterator = part4.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }

    public Iterator<String> iterator() {
        String s = Part1.readFile("main.part4.txt");
        Pattern pattern = Pattern.compile("[A-ZА-ЯЁІЇЄ][a-zа-яёїіє,\\s]+\\.");
        Matcher matcher = pattern.matcher(s);
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return matcher.find();
            }

            @Override
            public String next() {
                if (matcher.hitEnd()) throw new NoSuchElementException();
                return matcher.group().replace(System.lineSeparator(), " ");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
