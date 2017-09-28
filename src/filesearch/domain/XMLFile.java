package filesearch.domain;

import java.util.LinkedList;
import java.util.List;

public class XMLFile {

    private final String path;
    private final List<URLLink> links;

    public XMLFile(String path) {
        this.path = path;
        this.links = new LinkedList<>();
    }

    public void addURL(URLLink link) {
        links.add(link);
    }

    public String getPath() {
        return path;
    }

    public List<URLLink> getLinks() {
        return links;
    }
}
