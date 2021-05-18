package main.part7.controller;

import main.part7.entity.Film;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DOMController {
    private String fileName;
    private List<Film> films = new ArrayList<>();

    public List<Film> getList(){
        return films;
    }

    public DOMController(String fileName) {
        this.fileName = fileName;
    }

    public void readFileDOM() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setExpandEntityReferences(false);
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileName));
        NodeList filmList = document.getDocumentElement().getElementsByTagNameNS("*", "film");
        for (int i = 0; i < filmList.getLength(); i++) {
            Node film = filmList.item(i);
            NodeList childNodes = film.getChildNodes();
            childNodesMethod(childNodes);
        }
    }

    public void childNodesMethod (NodeList childNodes) {
        Film filmItem = new Film();
        for (int b = 0; b < childNodes.getLength(); b++) {
            if (childNodes.item(b).getPrefix() != null) {
                if (childNodes.item(b).getLocalName().equals("id")) filmItem.setId(Integer.parseInt(childNodes.item(b).getFirstChild().getTextContent()));
                if (childNodes.item(b).getLocalName().equals("title")) filmItem.setTitle(childNodes.item(b).getFirstChild().getTextContent());
                if (childNodes.item(b).getLocalName().equals("year")) filmItem.setYear(Integer.parseInt(childNodes.item(b).getFirstChild().getTextContent()));
                if (childNodes.item(b).getLocalName().equals("genre")) filmItem.setGenre(Film.Genre.valueOf(childNodes.item(b).getFirstChild().getTextContent().toUpperCase(Locale.ROOT)));
            }
        }
        films.add(filmItem);
    }

    public static void saveToFile(List<Film> films, String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setExpandEntityReferences(false);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("filmoteka");
            doc.appendChild(rootElement);
            for (Film film : films)
                rootElement.appendChild(getFilm(doc, film));

            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File(filename));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // метод для создания нового узла XML-файла
    private static Node getFilm(Document doc, Film film) {
        Element filmInit = doc.createElement("film");
        filmInit.appendChild(getFilmElements(doc, "id", String.valueOf(film.getId())));
        filmInit.appendChild(getFilmElements(doc, "title", film.getTitle()));
        filmInit.appendChild(getFilmElements(doc, "year", String.valueOf(film.getYear())));
        filmInit.appendChild(getFilmElements(doc, "genre", film.getGenre().getValue()));
        return filmInit;
    }

    // утилитный метод для создание нового узла XML-файла
    private static Node getFilmElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}


