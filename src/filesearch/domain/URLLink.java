package filesearch.domain;

public class URLLink {

    private final int lineNumber;
    private final String url;

    public URLLink(int lineNumber, String url) {
        this.lineNumber = lineNumber;
        this.url = url;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getURL() {
        return url;
    }
}
