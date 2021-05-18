package main.part7.controller;

import main.part7.entity.Film;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StAXController {
    private String fileName;
    private List<Film> films = new ArrayList<>();
    private int id;
    private String title;
    private int year;
    private Film.Genre genre;

    public List<Film> getList(){
        return films;
    }

    public StAXController(String fileName) {
        this.fileName = fileName;
    }

    public void readFileStAX() {
        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(Paths.get(fileName)))) {
            XMLEventReader reader = processor.getReader();
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    String a = reader.nextEvent().toString();
                    StartElement el = event.asStartElement();
                    switchCase(el.getName().getLocalPart(), a);
                }
                if (id > 0 && title != null && genre != null && year > 0) {
                    films.add(new Film(id, title, year, genre));
                    title = "";
                    id = 0;
                    genre = null;
                    year = 0;
                }
            }
        } catch (XMLStreamException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void switchCase(String el, String name) {
        if (!name.isEmpty())
            switch (el) {
                case "id":
                    id = Integer.parseInt(name);
                    break;
                case "title":
                    title = name;
                    break;
                case "year":
                    year = Integer.parseInt(name);
                    break;
                case "genre":
                    genre = Film.Genre.valueOf(name.toUpperCase(Locale.ROOT));
                    break;
                default:
                    break;
            }
    }

    public class StaxStreamProcessor implements AutoCloseable {
        private final XMLInputFactory factory = XMLInputFactory.newInstance();
        private XMLEventReader reader;

        public StaxStreamProcessor(InputStream is) throws XMLStreamException {
            reader = factory.createXMLEventReader(is);
        }

        public XMLEventReader getReader() {
            return reader;
        }

        @Override
        public void close() {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

