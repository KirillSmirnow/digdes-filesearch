package filesearch.domain;

import java.util.LinkedList;
import java.util.List;

public class MyZipFile {

    private final String path;
    private final List<XMLFile> xmlFiles;

    public MyZipFile(String path) {
        this.path = path;
        this.xmlFiles = new LinkedList<>();
    }

    public void addFile(XMLFile file) {
        xmlFiles.add(file);
    }

    public String getPath() {
        return path;
    }

    public List<XMLFile> getXmlFiles() {
        return xmlFiles;
    }
}
