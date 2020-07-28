package pages.mfinans;

import drivers.manager.DriverManager;
import generic.assertions.AssertWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
        Assert.assertEquals(listOfImgs.size(), 5);

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

    public AboutPage clickImgDetailsContains(int imgNumber, String value) {
        WaitForElement.waitUntilElementIsVisibleRefreshed(gallery);
        List<WebElement> listOfImgs = gallery.findElements(By.cssSelector("img"));
        WebElement element = listOfImgs.get(imgNumber-1);
        ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        WaitForElement.waitUntilElementIsClickable(element);
        element.click();

        WebElement imgTitle = DriverManager.getWebDriver().findElement(By.cssSelector("div.lb-details span.lb-caption"));
        WaitForElement.waitUntilElementIsVisible(imgTitle);
        AssertWebElement.assertThat(imgTitle).containsText(value);

        WebElement webElement = DriverManager.getWebDriver().findElement(By.cssSelector("a.lb-close"));
        WaitForElement.waitUntilElementIsClickable(webElement);
        webElement.click();
        WaitForElement.waitUntilElementIsClickable(webElement, 5);

        return this;
    }
}
