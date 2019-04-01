package testing.entity;

public enum WebBrowser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge"),
    PHANTOM_JS("phantomjs");

    private String name;

    WebBrowser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
