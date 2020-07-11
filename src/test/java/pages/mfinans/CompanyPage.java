package pages.mfinans;

import drivers.manager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class CompanyPage extends BasePage {

    private Logger logger = LogManager.getLogger(CompanyPage.class);

    public CompanyPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }
}
