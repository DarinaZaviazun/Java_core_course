package main.part7.controller;

import main.part7.entity.Film;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SAXController {
    private static ArrayList<Film> films = new ArrayList<>();
    private String fileName;

    public List<Film> getList(){
        return films;
    }
    public SAXController(String fileName) {
        this.fileName = fileName;
    }

    public void readFileSAX() throws SAXNotSupportedException, SAXNotRecognizedException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Handler handler = new Handler();
        factory.setNamespaceAware(true);
        try {
            factory.setFeature("http://xml.org/sax/features/validation", true);
            factory.setFeature("http://apache.org/xml/features/validation/schema", true);
            factory.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
        } catch (ParserConfigurationException | SAXNotRecognizedException | SAXNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(fileName), handler);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }


    }


    private static class Handler extends DefaultHandler {
        private String id;
        private String title;
        private String year;
        private String genre;
        private String lastElementName;
        private Map<String, String> attribute = new LinkedHashMap<>();

        @Override
        public void characters(char[] ch, int start, int length) {
            String info = new String(ch, start, length);
            info = info.replace(System.lineSeparator(), "").trim();
            if (!info.isEmpty()) {
                if (lastElementName.equals("id")) id = info;
                if (lastElementName.equals("title")) title = info;
                if (lastElementName.equals("genre")) genre = info;
                if (lastElementName.equals("year")) year = info;
            }
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (attributes.getValue(0) != null && !attributes.getValue(0).isEmpty()) {
                for (int a = 0; a < attributes.getLength(); a++) {
                    attribute.put(attributes.getQName(a), attributes.getValue(a));
                }
            }
            lastElementName = localName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if ((id != null && !id.isEmpty()) && (title != null && !title.isEmpty()) && (year != null && !year.isEmpty()) && (genre != null && !genre.isEmpty())) {
                Film newFilm = new Film();
                newFilm.setId(Integer.parseInt(id));
                newFilm.setTitle(title);
                newFilm.setGenre(Film.Genre.valueOf(genre.toUpperCase()));
                newFilm.setYear(Integer.parseInt(year));
                films.add(newFilm);
                id = null;
                title = null;
                genre = null;
                year = null;
            }
        }
    }
}
