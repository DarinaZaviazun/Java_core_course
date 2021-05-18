package practice7.controller;

import main.part7.controller.DOMController;
import main.part7.controller.SAXController;
import main.part7.controller.StAXController;
import main.part7.entity.Film;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ControllerTest {
    String tryNew = "[Film{id=1, title='\"Harry Potter and the Sorcerer's Stone\"', year=2001, genre=FAMILY_MOVIE}, Film{id=2, title='\"Pulp Fiction\"', year=1994, genre=THRILLER}, Film{id=3, title='\"The Great Gatsby\"', year=2013, genre=DRAMA}]";

    @Test
    public void xmlTest() {
        SAXController saxController = new SAXController("input.xml");
        try {
            saxController.readFileSAX();
        } catch (SAXNotSupportedException | SAXNotRecognizedException | ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(saxController.getList().toString(), tryNew);
    }

    @Test
    public void xmlTest2() {
        StAXController staxController = new StAXController("input.xml");
        staxController.readFileStAX();
        assertEquals(staxController.getList().toString(), tryNew);
    }

    @Test
    public void xmlTest3() {
        DOMController domController = new DOMController("input.xml");
        try {
            domController.readFileDOM();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(domController.getList().toString(), tryNew);
    }

    @Test
    public void filmTest(){
        Film film = new Film(7, "Zolushka", 1999, Film.Genre.DRAMA);
        assertEquals(7, film.getId());
        assertEquals("Zolushka", film.getTitle());
        assertEquals(1999, film.getYear());
        assertEquals(Film.Genre.DRAMA, film.getGenre());
        assertEquals(Film.Genre.DRAMA.getValue(), ("Drama"));
    }
}
