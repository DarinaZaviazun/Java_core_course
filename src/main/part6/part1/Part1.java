package main.part6.part1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Part1 {

    public static void main(String[] args) {
        String mock = "asd 43 asdf asd 43" +
                System.lineSeparator() +
                "434 asdf" +
                System.lineSeparator() +
                "kasdf asdf stop asdf nnn" +
                System.lineSeparator() +
                "stop";
        InputStream anyInputStream = new ByteArrayInputStream(mock.getBytes());
        System.setIn(anyInputStream);
        WordContainer.main(null);
    }
}
