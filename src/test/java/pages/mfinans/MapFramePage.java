package pages.mfinans;

import drivers.manager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class MapFramePage extends ContactPage {

    private Logger logger = LogManager.getLogger(MapFramePage.class);

    public MapFramePage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }
}
