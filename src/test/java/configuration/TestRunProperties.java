package configuration;

import drivers.BrowserType;

public class TestRunProperties {

    public static String getGridUrl() {
        return ConfigurationProperties.getProperties().getProperty("grid.url");
    }

    public static BrowserType getBrowserToRun(){
        return BrowserType.valueOf(ConfigurationProperties.getProperties().getProperty("default.browser"));
    }

    public static boolean getIsRemoteRun(){
        return Boolean.parseBoolean(ConfigurationProperties.getProperties().getProperty("app.is_remote.run"));
    }
}
