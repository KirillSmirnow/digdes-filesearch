package filesearch.search;

class URLVerifier {

    static boolean isURL(String s) {
        return s.toLowerCase().matches("[a-z][a-z0-9+.-]*:[\\S]+");
    }
}
