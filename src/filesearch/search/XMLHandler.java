package filesearch.search;

import filesearch.domain.URLLink;
import filesearch.domain.XMLFile;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class XMLHandler extends DefaultHandler {

    private final XMLFile xmlFile;
    private Locator locator;

    XMLHandler(XMLFile xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); ++i) {
            String value = attributes.getValue(i);
            if (!URLVerifier.isURL(value)) continue;
            URLLink link = new URLLink(locator.getLineNumber(), value);
            xmlFile.addURL(link);
        }
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
}
