package practice6.part4;

import main.part6.part4.Range;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RangeTest {

    @Test
    public void main(){
        Range range = new Range(3, 11);
        StringBuilder sb = new StringBuilder();
        for (Integer el : range) {
            sb.append(el).append(" ");
        }
        assertEquals("3 4 5 6 7 8 9 10 11 ", sb.toString());

        range = new Range(4, 10, true);
        sb = new StringBuilder();
        for (Integer el : range) {
            sb.append(el).append(" ");
        }
        assertEquals("10 9 8 7 6 5 4 ", sb.toString());
    }
}
