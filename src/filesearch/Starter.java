package filesearch;

import filesearch.domain.MyZipFile;
import filesearch.domain.URLLink;
import filesearch.domain.XMLFile;
import filesearch.search.FileExplorer;

import java.util.List;

public class Starter {

    private static final Printer PRINTER = new Printer();

    public static void main(String[] args) {
        if (args.length < 1) {
            PRINTER.println("usage: <FileSearch> directories");
            System.exit(-1);
        }
        for (String dirPath : args)
            try {
                FileExplorer explorer = new FileExplorer(dirPath);
                explorer.explore();
                printLinks(dirPath, explorer.getMyZipFiles());
            } catch (Exception e) {
                PRINTER.println("Error: " + e.getMessage());
            }
    }

    private static void printLinks(String dirPath, List<MyZipFile> myZipFiles) {
        PRINTER.println("Exploring directory " + dirPath + "\n");
        if (myZipFiles.isEmpty()) {
            PRINTER.println("URLs not found\n\n");
            return;
        }
        for (MyZipFile zipFile : myZipFiles) {
            PRINTER.println("Zip file: " + zipFile.getPath());
            for (XMLFile xmlFile : zipFile.getXmlFiles()) {
                PRINTER.println("\tXML file: " + xmlFile.getPath());
                for (URLLink link : xmlFile.getLinks())
                    PRINTER.println("\t\tline = " + link.getLineNumber() + ", url = " + link.getURL());
            }
            PRINTER.println("");
        }
        PRINTER.println("\n");
    }
}
