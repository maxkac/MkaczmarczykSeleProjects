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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static generic.LoggerStrings.*;

public class ContactFormPage extends BasePage {

    @FindBy(id = "contact")
    private WebElement contactForm;

    @FindBy(id = "agreement")
    private WebElement agreeSection;

    @FindBy(css = "#contact input[name='name']")
    private WebElement nameInput;

    @FindBy(css = "#contact input[name='email']")
    private WebElement emailInput;

    @FindBy(css = "#contact input[name='phone']")
    private WebElement phoneInput;

    @FindBy(css = "#agreement input")
    private List<WebElement> checkBoxesAgree;

    @FindBy(css = "#agreement small")
    private List<WebElement> moreInfoAgrees;

    @FindBy(css = "#agreement span")
    private List<WebElement> closeMoreInfoAgrees;

    @FindBy(css = "input[type='submit']")
    private WebElement sendBtn;

    private Logger logger = LogManager.getLogger(ContactFormPage.class);

    public ContactFormPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    @Step("TYpe name from parameter.")
    public ContactFormPage typeName(String name) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();
        executor.executeScript("arguments[0].scrollIntoView();", contactForm);
        logger.info(SCROLL.getMsg(), contactForm);
        WaitForElement.waitUntilElementIsClickable(nameInput);
        logger.info(EDIT.getMsg());
        nameInput.clear();
        logger.info(CLEAR.getMsg());
        nameInput.sendKeys(name);

        return this;
    }

    @Step("TYpe mail from parameter.")
    public ContactFormPage typeMail(String mail) {
        WaitForElement.waitUntilElementIsClickable(emailInput);
        logger.info(EDIT.getMsg());
        emailInput.clear();
        logger.info(CLEAR.getMsg());
        emailInput.sendKeys(mail);

        return this;
    }

    @Step("TYpe phone number from parameter.")
    public ContactFormPage typePhone(String phone) {
        WaitForElement.waitUntilElementIsClickable(phoneInput);
        logger.info(EDIT.getMsg());
        phoneInput.clear();
        logger.info(CLEAR.getMsg());
        phoneInput.sendKeys(phone);

        return this;
    }

    @Step("Select all agree")
    public ContactFormPage clickAllAgree() {
        WaitForElement.waitUntilElementIsClickable(checkBoxesAgree.get(0));
        checkBoxesAgree.forEach(element -> {
            WaitForElement.waitUntilElementIsClickable(element);
            logger.info(CLICK.getMsg());
            element.click();
            AssertWebElement.assertThat(element).isSelected();
        });

        return this;
    }

    @Step("Click all 'More info'.")
    public ContactFormPage showAllInfo() {
        WaitForElement.waitUntilElementIsClickable(moreInfoAgrees.get(0));
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();
        AtomicInteger count = new AtomicInteger();
        moreInfoAgrees.forEach(element -> {
            WaitForElement.waitUntilElementIsClickable(element);
            logger.info(CLICK.getMsg());
            executor.executeScript("arguments[0].scrollIntoView();", element);
            logger.info(SCROLL.getMsg(), element);
            element.click();
            List<WebElement> elements = agreeSection.findElements(By.cssSelector("span")).stream()
                    .filter(WebElement::isDisplayed).collect(Collectors.toList());
            logger.info("List of crosses size = {}", elements.size());
            Assert.assertEquals(count.incrementAndGet(), elements.size());
        });
        return this;
    }

    @Step("Click all close 'More info'.")
    public ContactFormPage closeAllInfo() {
        WaitForElement.waitUntilElementIsClickable(closeMoreInfoAgrees.get(0));
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();
        executor.executeScript("arguments[0].scrollIntoView();", moreInfoAgrees.get(0));
        logger.info(SCROLL.getMsg(), moreInfoAgrees.get(0));
        AtomicInteger count = new AtomicInteger(4);
        closeMoreInfoAgrees.forEach(crossBtn -> {
            WaitForElement.waitUntilElementIsClickable(crossBtn);
            logger.info(CLICK.getMsg());
            crossBtn.click();
            List<WebElement> elements = agreeSection.findElements(By.cssSelector("span")).stream()
                    .filter(WebElement::isDisplayed).collect(Collectors.toList());
            logger.info("List of crosses size = {}", elements.size());
            Assert.assertEquals(count.decrementAndGet(), elements.size());
        });

        return this;
    }

    @Step("Click 'Send'")
    public IndexPage clickSendContact() {
        WaitForElement.waitUntilElementIsClickable(sendBtn);
        logger.info(CLICK.getMsg());
        sendBtn.click();

        return new IndexPage();
    }

    @Step("Click index of agrees, index in parameter.")
    public ContactFormPage clickAgree(int agreeIndex) {
        WaitForElement.waitUntilElementIsClickable(checkBoxesAgree.get(agreeIndex - 1));
        logger.info(CLICK.getMsg());
        checkBoxesAgree.get(agreeIndex - 1).click();
        AssertWebElement.assertThat(checkBoxesAgree.get(agreeIndex - 1)).isSelected();
        return this;
    }
}
