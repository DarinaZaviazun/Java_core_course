package main.part7;

import main.part7.controller.DOMController;
import main.part7.controller.SAXController;
import main.part7.controller.StAXController;
import main.part7.entity.Film;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        String xmlFile = args[0];
        SAXController saxController = new SAXController(xmlFile);
        saxController.readFileSAX();
        List<Film> collect = saxController.getList().stream().sorted((a, b) -> b.getTitle().compareTo(a.getTitle())).collect(Collectors.toList());
        DOMController.saveToFile(collect, "output.sax.xml");

        DOMController domController = new DOMController(xmlFile);
        domController.readFileDOM();
        List<Film> collect2 = domController.getList().stream().sorted((a, b) -> b.getYear() - a.getYear()).collect(Collectors.toList());
        DOMController.saveToFile(collect2, "output.dom.xml");

        StAXController stAXController = new StAXController(xmlFile);
        stAXController.readFileStAX();
        List<Film> collect3 = stAXController.getList().stream().sorted((a, b) -> b.getId() - a.getId()).collect(Collectors.toList());
        DOMController.saveToFile(collect3, "output.stax.xml");
    }
}
