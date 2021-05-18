package practice6.part1;


import main.part6.part1.WordContainer;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class WordContainerTest {
    @Test
    public void main(){
        String mock = "asd 43 asdf asd 43" +
                System.lineSeparator() +
                "434 asdf" +
                System.lineSeparator() +
                "kasdf asdf stop asdf nnn" +
                System.lineSeparator() +
                "stop";
        InputStream anyInputStream = new ByteArrayInputStream(mock.getBytes());
        System.setIn(anyInputStream);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        WordContainer.main(null);
        assertEquals("asdf : 3" + System.lineSeparator() +
                "43 : 2" + System.lineSeparator() +
                "asd : 2" + System.lineSeparator() +
                "434 : 1" + System.lineSeparator() +
                "kasdf : 1" + System.lineSeparator(), outContent.toString());
    }
}
