package practice6.part5;

import main.part6.part5.Tree;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TreeTest {

    @Test
    public void main(){
        Tree<Integer> nodes = new Tree<>();
        nodes.add(3);
        nodes.add(7);
        nodes.add(1);
        nodes.add(8);
        nodes.add(6);
        nodes.add(2);
        nodes.add(5);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String s = System.lineSeparator();
        String needed ="  1" + s + "    2" + s + "3" + s + "      5" + s + "    6" + s + "  7" + s + "    8" + s;
        nodes.print();
        assertEquals(needed, outputStream.toString());
    }
}
