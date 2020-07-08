package drivers;

public enum BrowserType {

    FIREFOX("firefox"),
    CHROME("chrome"),
    IE("internetexplorer"),
    OPERA("opera");

    private final String browser;

    BrowserType(String browser) {
        this.browser = browser;
    }
}
