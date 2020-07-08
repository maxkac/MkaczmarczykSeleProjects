package configuration;

public class AppProperties {
    //Metoda zwraca property o nazwie app.url wywołując metodę getProperty, na obiekcie zwracanym przez
    // metodę getProperties z klasy ConfigurationProperties
    public static String getHldUrl(){
        return ConfigurationProperties.getProperties().getProperty("app.urls.hld");
    }

    public static String getMainUrl() {
        return ConfigurationProperties.getProperties().getProperty("app.urls.main");
    }

}
