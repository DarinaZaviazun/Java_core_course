package practice6.part2;

import main.part6.part2.Part2;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class Part2Test {
    @Test
    public void main(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Part2.main(null);
        String s = outputStream.toString();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(s);
        int[] rezults = new int[4];
        int a = 0;
        while (matcher.find()){
            rezults[a] = Integer.parseInt(matcher.group());
            a++;
        }
        assertTrue(rezults[1] / rezults[0] > 4);
        assertTrue(rezults[2] > rezults[3]);
    }
}
