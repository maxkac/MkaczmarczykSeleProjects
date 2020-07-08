package pages.mfinans;

import drivers.manager.DriverManager;
import generic.assertions.AssertWebElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;

import static generic.LoggerStrings.VISIBLE;

public class ContactPage extends BasePage {

    private Logger logger = LogManager.getLogger(ContactPage.class);

    public ContactPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    @FindBy(css = "iframe")
    private WebElement mapFrame;


    @Step("Check map is Visible.")
    public ContactPage mapIsVisible() {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        WaitForElement.waitUntilElementIsVisible(mapFrame);
        logger.info(VISIBLE.getMsg());
        AssertWebElement.assertThat(mapFrame).isDisplayed();
        logger.info("Assertion element {} is displayed.", mapFrame);

        return this;
    }

    @Step("Switch to first frame on page.")
    public MapFramePage switchToFrame() {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        DriverManager.getWebDriver().switchTo().frame(mapFrame);
        logger.info("Switched to frame.");

        return new MapFramePage();
    }
}
