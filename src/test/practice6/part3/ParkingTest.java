package practice6.part3;

import main.part6.part3.Parking;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void main (){
        Parking parking = new Parking(4);
        assertTrue(parking.arrive(2));
        assertTrue(parking.arrive(3));
        assertTrue(parking.arrive(2));
        assertTrue(parking.arrive(2));
        assertFalse(parking.arrive(2));
        assertTrue(parking.depart(1));
        assertFalse(parking.depart(1));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        parking.print();
        assertEquals("1011", outputStream.toString());
    }
}
