package pages.mfinans;

import drivers.manager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import waits.WaitForElement;

import java.util.List;

public class AboutPage extends BasePage {

    @FindBy(className = "galery")
    private WebElement gallery;

    private Logger logger = LogManager.getLogger(BasePage.class);

    public AboutPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    public AboutPage checkCountOfImgsInGallery() {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        List<WebElement> listOfImgs = gallery.findElements(By.cssSelector("img"));
        Assert.assertTrue(listOfImgs.size() == 5);

        return this;
    }

    public AboutPage clickAndExitAllImgs() {
        WaitForElement.waitUntilElementIsVisibleRefreshed(gallery);
        List<WebElement> listOfImgs = gallery.findElements(By.cssSelector("img"));
        listOfImgs.forEach(e -> {
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", e);
            WaitForElement.sleep(500);
            WaitForElement.waitUntilElementIsClickable(e);
            e.click();
            WebElement webElement = DriverManager.getWebDriver().findElement(By.cssSelector("a.lb-close"));
            WaitForElement.waitUntilElementIsClickable(webElement);
            webElement.click();
            WaitForElement.waitUntilElementIsClickable(webElement, 5);
        });
        return this;
    }
}
