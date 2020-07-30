package pages.mfinans;

import drivers.manager.DriverManager;
import generic.assertions.AssertWebElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import waits.WaitForElement;

import java.io.*;
import java.util.Arrays;

import static generic.LoggerStrings.VISIBLE;

public class BasePage {

    private Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    @FindBy(id = "navbar")
    protected WebElement naviTopBar;

    @FindBy(id = "menu-kredyty")
    protected WebElement listWithLoanKind;

    @FindBy(css = "body")
    protected  WebElement body;

    @FindBy(css = "div.atributes")
    protected  WebElement atr;

    @FindBy(css = "div.content")
    protected  WebElement content;

    @FindBy(css = "div.footer")
    protected  WebElement footer;

    @Step("Check that page contains text {value}.")
    public BasePage pageContains(String value) {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        AssertWebElement.assertThat(body).containsText(value);
        return this;
    }

    @Step("Check that page contains texts from array {value}.")
    public BasePage pageContains(String ... value) {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        Arrays.asList(value).forEach(e -> {
            pageContains(e);
            logger.info(VISIBLE.getMsg());
        });
        return this;
    }

    @Step("Back to parent frame.")
    public BasePage switchToParentFrame() {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        DriverManager.getWebDriver().switchTo().parentFrame();
        logger.info("Switched to parent frame.");

        return this;
    }

    @Step("Check that page have clickable element {by}.")
    public BasePage elementIsClickable(By by) {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        WebElement element = body.findElement(by);
        logger.info("Element {} found.", element);
        WaitForElement.waitUntilElementIsClickable(element);
        logger.info("Element {} is clickable.", element);
        AssertWebElement.assertThat(element).isDisplayed();

        return this;
    }

    @Step("Check that title is expected.")
    public BasePage titleIs(String value) {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        String pageTitle = DriverManager.getWebDriver().getTitle();
        logger.info("Title found.");
        Assert.assertEquals(pageTitle, value);
        logger.info("Assertion passed, pageTitle == {}", value);

        return this;
    }

    @Step("Check that element ({by}) is visible.")
    public BasePage elementIsVisible(By by) {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        WebElement element = body.findElement(by);
        logger.info("Element {} found.", element);
        WaitForElement.waitUntilElementIsVisible(element);
        logger.info(VISIBLE.getMsg());
        AssertWebElement.assertThat(element).isDisplayed();

        return this;
    }

    @Step("Compare page {content} with text from text file.")
    public BasePage compareTxtWithTxtFile(File file) {
        WaitForElement.waitUntilElementIsVisibleRefreshed(body);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.readLine() != null){
                String line = bufferedReader.readLine();
                logger.info("Read line ({}) from file ({})", line, file.getAbsolutePath());
                AssertWebElement.assertThat(content).containsText(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return this;
    }

    @Step("Check that element by ({by}) contains text.")
    public BasePage elementContainsText(By by, String... args) {
        WebElement element = DriverManager.getWebDriver().findElement(by);
        logger.info("Element {} found.", element);
        WaitForElement.waitUntilElementIsVisibleRefreshed(element);
        Arrays.asList(args).forEach(e -> {
            AssertWebElement.assertThat(element).containsText(e);
            logger.info(VISIBLE.getMsg());
        });

        return this;
    }
}
