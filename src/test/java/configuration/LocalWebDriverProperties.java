package configuration;

public class LocalWebDriverProperties {
    // Metody zwracają właściwości dla poszczególnych kluczy, analogicznie jak w przypadku AppProperties

    public static String getChromeWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("drivers.location.chrome");
    }

    public static String getFirefoxWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("drivers.location.firefox");
    }

    public static String getInternetExplorerWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("drivers.location.ie");
    }

    public static String getOperaWebDriverLocation() {
        return ConfigurationProperties.getProperties().getProperty("drivers.location.opera");
    }
}
