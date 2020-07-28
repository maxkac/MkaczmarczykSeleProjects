package pages.mfinans;

import drivers.manager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactFormPage extends BasePage {

    @FindBy(id = "contact")
    private WebElement contactForm;

    private Logger logger = LogManager.getLogger(ContactFormPage.class);

    public ContactFormPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

}
