package filesearch.search;

import filesearch.domain.MyZipFile;
import filesearch.domain.XMLFile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileExplorer {

    private final File rootDirectory;
    private final List<MyZipFile> myZipFiles;

    public FileExplorer(String rootDirectoryPath) {
        this.rootDirectory = new File(rootDirectoryPath);
        this.myZipFiles = new LinkedList<>();
        if (!rootDirectory.exists())
            throw new RuntimeException("directory does not exist: " + rootDirectoryPath);
        if (!rootDirectory.isDirectory())
            throw new RuntimeException("directory not found: " + rootDirectoryPath);
    }

    public void explore() {
        findZips(rootDirectory);
    }

    public List<MyZipFile> getMyZipFiles() {
        return myZipFiles;
    }

    private void findZips(File parentDirectory) {
        File[] files = parentDirectory.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                findZips(file);
            } else {
                ZipFile zipFile;
                try {
                    zipFile = new ZipFile(file);
                } catch (Exception e) {
                    // not zip
                    continue;
                }
                try {
                    MyZipFile myZipFile = new MyZipFile(file.getCanonicalPath());
                    exploreZip(myZipFile, zipFile);
                    if (!myZipFile.getXmlFiles().isEmpty())
                        myZipFiles.add(myZipFile);
                } catch (Exception ignored) {
                }
            }
        }
    }

    private void exploreZip(MyZipFile myZipFile, ZipFile zipFile) {
        for (ZipEntry entry : zipFile.stream().collect(Collectors.toSet())) {
            if (entry.isDirectory()) continue;
            XMLFile xmlFile = new XMLFile(entry.getName());
            try {
                new XMLExplorer(xmlFile, zipFile.getInputStream(entry))
                        .explore();
            } catch (Exception e) {
                continue;
            }
            if (!xmlFile.getLinks().isEmpty())
                myZipFile.addFile(xmlFile);
        }
    }
}
