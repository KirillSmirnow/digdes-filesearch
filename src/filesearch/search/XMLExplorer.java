package filesearch.search;

import filesearch.domain.XMLFile;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

class XMLExplorer {

    private static final SAXParserFactory SAX_PARSER_FACTORY = SAXParserFactory.newInstance();

    private final SAXParser parser;

    private final XMLFile xmlFile;
    private final InputStream inputStream;

    XMLExplorer(XMLFile xmlFile, InputStream inputStream) {
        this.xmlFile = xmlFile;
        this.inputStream = inputStream;
        try {
            this.parser = SAX_PARSER_FACTORY.newSAXParser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void explore() {
        try {
            parser.parse(inputStream, new XMLHandler(xmlFile));
        } catch (Exception ignored) {
        }
    }
}
