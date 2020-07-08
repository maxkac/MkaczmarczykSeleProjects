package globalMethods;

import drivers.manager.DriverManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import waits.WaitForElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class BasicSteps {

    static Logger logger = LogManager.getLogger(globalMethods.BasicSteps.class);

    private static String chars = "qwertyuiopasdfghjklzxcvbnm";
    private static String specialChars = " .@_";
    private static Random random = new Random();

    @Step("Kliknięcie w element")
    public static void firstLevelClick(WebElement element){
        WaitForElement.waitUntilElementIsClickable(element);
        logger.info("Element '{}' is clickable, try to click them.", element.getText());
        element.click();
    }

    @Step("Przesunięcie scrollem aby element był widoczny.")
    public static void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
        logger.info("Scrolled to element.");
    }

    @Step("klikniecie w element z rozwijanego menu.")
    public static void secondLevelClick(WebElement element1, WebElement element2){
//        WebElement webElement = DriverManager.getWebDriver().findElement(By.id("_ebp_btnLogOut"));
//        WaitForElement.waitUntilElementIsVisible(webElement);
        WaitForElement.waitUntilElementIsClickable(element1);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();

        logger.info("Element '{}' is clickable, try to click them.", element1.getText().equals("")?element1.getAttribute("value"):element1.getText());
        try {
            element1.click();
            js.executeScript("arguments[0].scrollIntoView();", element2);
            element2.click();
        }catch (ElementNotInteractableException | NoSuchElementException exception){
            logger.error("element not interactable, trying again...");
            WaitForElement.waitUntilElementIsVisible(element1);
            element1.click();
            js.executeScript("arguments[0].scrollIntoView();", element2);
            element2.click();
        }
    }

    @Step("Parsowanie pliku ({filePath}) z danymi tekstowymi do Stringa.")
    public static String fileToString(String filePath) {
        String text = "";
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                text = text + scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return text;
    }

    @Step("Wygenerowanie losowego ciągu znaków o długości {size}.")
    public static String randomString(int size){
        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i < size; i++){
            String singleChar = String.valueOf(chars.charAt(random.nextInt(chars.length())));
            String specialChar = String.valueOf(specialChars.charAt(random.nextInt(specialChars.length())));
            int randomTypeCharInt = random.nextInt(6);
            if(randomTypeCharInt == 4 && i != size-1) {
                result.append(specialChar);
            }else if(randomTypeCharInt == 3){
                result.append(random.nextInt(9));
            }
            else if(randomTypeCharInt == 1){
                result.append(singleChar.toUpperCase());
            }else {
                result.append(singleChar);
            }
        }
        return result.toString();
    }

    @Step("Wprowadzenie ciągu znaku '{value}' do WebElementu '{element}'.")
    public static void typeValue(String value, WebElement element) {
        element.clear();
        element.sendKeys(value);
    }
}
