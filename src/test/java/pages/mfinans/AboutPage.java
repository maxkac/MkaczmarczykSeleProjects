package pages.mfinans;

import drivers.manager.DriverManager;
import generic.assertions.AssertWebElement;
import io.qameta.allure.Step;
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

import static generic.LoggerStrings.*;

public class AboutPage extends BasePage {

    @FindBy(className = "galery")
    private WebElement gallery;

    private Logger logger = LogManager.getLogger(BasePage.class);

    public AboutPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    @Step("Check count of images in gallery.")
    public AboutPage checkCountOfImgsInGallery() {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        List<WebElement> listOfImgs = gallery.findElements(By.cssSelector("img"));
        logger.info("Found and add elements to list, list size = {}", listOfImgs.size());
        Assert.assertEquals(listOfImgs.size(), 5);

        return this;
    }

    @Step("Open and close all images.")
    public AboutPage clickAndExitAllImgs() {
        WaitForElement.waitUntilElementIsVisibleRefreshed(gallery);
        List<WebElement> listOfImgs = gallery.findElements(By.cssSelector("img"));
        logger.info("Found and add elements to list, list size = {}", listOfImgs.size());
        listOfImgs.forEach(e -> {
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", e);
            logger.info(SCROLL.getMsg(), e);
            WaitForElement.sleep(500);
            logger.info("Wait 500 ms.");
            WaitForElement.waitUntilElementIsClickable(e);
            logger.info(CLICK.getMsg());
            e.click();
            WebElement webElement = DriverManager.getWebDriver().findElement(By.cssSelector("a.lb-close"));
            logger.info("Found element ({}).", webElement);
            WaitForElement.waitUntilElementIsClickable(webElement);
            logger.info(CLICK.getMsg());
            webElement.click();
            WaitForElement.waitUntilElementIsClickable(webElement, 5);
        });
        return this;
    }

    @Step("Open image (index = {imgNumber}) and check details ({value}).")
    public AboutPage clickImgDetailsContains(int imgNumber, String value) {
        WaitForElement.waitUntilElementIsVisibleRefreshed(gallery);
        List<WebElement> listOfImgs = gallery.findElements(By.cssSelector("img"));
        logger.info("Found and add elements to list, list size = {}", listOfImgs.size());
        WebElement element = listOfImgs.get(imgNumber-1);
        logger.info("Get element ({}) by index from list.", element);
        ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        logger.info(SCROLL.getMsg(), element);
        WaitForElement.waitUntilElementIsClickable(element);
        logger.info(CLICK.getMsg());
        element.click();

        WebElement imgTitle = DriverManager.getWebDriver().findElement(By.cssSelector("div.lb-details span.lb-caption"));
        logger.info("Title of image found, value = {}", imgTitle);
        WaitForElement.waitUntilElementIsVisible(imgTitle);
        logger.info(VISIBLE.getMsg());
        AssertWebElement.assertThat(imgTitle).containsText(value);

        WebElement webElement = DriverManager.getWebDriver().findElement(By.cssSelector("a.lb-close"));
        logger.info("Button to close image found: {}", webElement);
        WaitForElement.waitUntilElementIsClickable(webElement);
        logger.info(CLICK.getMsg());
        webElement.click();
        WaitForElement.waitUntilElementIsClickable(webElement, 5);

        return this;
    }
}
