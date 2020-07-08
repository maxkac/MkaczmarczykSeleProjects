package drivers.manager;

import io.qameta.allure.Step;

public class DriverUtils {

    public static void setInitialConfiguration(){
        DriverManager.getWebDriver().manage().window().maximize();
    }

    @Step("Przejście na stronę: {urlPage}")
    public static void navigateToPage(String urlPage){
        DriverManager.getWebDriver().navigate().to(urlPage);
    }
}
