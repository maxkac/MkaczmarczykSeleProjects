package pages.mfinans;

import drivers.manager.DriverManager;
import generic.assertions.AssertWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;

import java.util.List;

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

    public ContactFormPage typeName(String name) {
        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getWebDriver();
        executor.executeScript("arguments[0].scrollIntoView();", contactForm);
        WaitForElement.waitUntilElementIsClickable(nameInput);
        logger.info(EDIT.getMsg());
        nameInput.clear();
        logger.info(CLEAR.getMsg());
        nameInput.sendKeys(name);

        return this;
    }

    public ContactFormPage typeMail(String mail) {
        WaitForElement.waitUntilElementIsClickable(emailInput);
        logger.info(EDIT.getMsg());
        emailInput.clear();
        logger.info(CLEAR.getMsg());
        emailInput.sendKeys(mail);

        return this;
    }

    public ContactFormPage typePhone(String phone) {
        WaitForElement.waitUntilElementIsClickable(phoneInput);
        logger.info(EDIT.getMsg());
        phoneInput.clear();
        logger.info(CLEAR.getMsg());
        phoneInput.sendKeys(phone);

        return this;
    }

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

    public ContactFormPage showAllInfo() {
        WaitForElement.waitUntilElementIsClickable(moreInfoAgrees.get(0));
        moreInfoAgrees.forEach(element -> {
            WaitForElement.waitUntilElementIsClickable(element);
            logger.info(CLICK.getMsg());
            element.click();
        });
        return this;
    }

    public IndexPage clickSendContact() {
        WaitForElement.waitUntilElementIsClickable(sendBtn);
        logger.info(CLICK.getMsg());
        sendBtn.click();

        return new IndexPage();
    }
}
