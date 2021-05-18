package practice6.part6;

import main.part6.part6.Part6;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class Part6Test {
    @Test
    public void main(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Part6.main(new String[] {"--input", "part6.txt", "--task", "frequency"});
        String s = System.lineSeparator();
        String freq = "whale ==> 3" + s +
                "cheetah ==> 4" + s +
                "bison ==> 3" + s;
        assertEquals(freq, outputStream.toString());
        Part6.main(new String[] {"--input", "part6.txt", "--task", "length"});
        String len = "chimpanzee ==> 10" + s +
                "mongoose ==> 8" + s +
                "tortoise ==> 8" + s;
        assertEquals(freq + len, outputStream.toString());
        Part6.main(new String[] {"--input", "part6.txt", "--task", "duplicates"});
        String dupl = "RAUGAJ" + s +
                "NOSIB" + s +
                "ELAHW" + s;
        assertEquals(freq + len + dupl, outputStream.toString());
    }
}
