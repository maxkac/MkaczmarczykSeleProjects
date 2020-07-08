package waits;

import drivers.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitForElement {

    private static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(DriverManager.getWebDriver(), 10);
    }

    public static void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitUntilElementIsVisible(WebElement element, int secondsToWait) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.withTimeout(secondsToWait, TimeUnit.SECONDS);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsClickable(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (StaleElementReferenceException e){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }
    public static void waitUntilElementIsClickable(WebElement element, int secondsToWait) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.withTimeout(secondsToWait, TimeUnit.SECONDS);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static boolean waitUntilElementIsVisibleReturnBoolean(WebElement element){
        WebDriverWait webDriverWait = getWebDriverWait();
        WebElement webElement = webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return webElement != null;
    }

    public static void waitUntilElementToBeSelected(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public static WebElement waitUntilElementIsVisibleAndReloaded(String css) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
        WebElement webElement = DriverManager.getWebDriver().findElement(By.cssSelector(css));
        return webElement;
    }

    public static void waitUntilElementIsLocated(By by) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForAllElementsInListIsVisible(List<WebElement> elements){
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(elements)));
    }

    public static void waitUntilElementIsClickableRefreshed(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        try {
            webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
        }catch (StaleElementReferenceException e){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    public static void waitUntilElementIsVisibleRefreshed(WebElement element) {
        sleep();
        WebDriverWait webDriverWait = getWebDriverWait();
        try {
            webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        }catch (StaleElementReferenceException e){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int milliSec) {
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitUntilElementIsInVisible(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }
}