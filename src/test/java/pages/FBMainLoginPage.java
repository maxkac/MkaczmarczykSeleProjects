package pages;

import drivers.manager.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.WaitForElement;

import static generic.LoggerStrings.*;

public class FBMainLoginPage {

    static Logger logger = LogManager.getLogger(FBMainLoginPage.class);

    @FindBy(id = "u_0_b")
    private WebElement loginBtn;

    @FindBy(id = "email")
    private WebElement mailLogin;

    @FindBy(id = "pass")
    private WebElement password;

    public FBMainLoginPage() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }

    public FBMainLoginPage typeData(String login, String pass) {
        WaitForElement.waitUntilElementIsClickable(loginBtn);
        logger.info(EDIT.getMsg());
        mailLogin.clear();
        mailLogin.sendKeys(login);

        password.clear();
        password.sendKeys(pass);

        return this;
    }

    public void clickLoginBtn() {
        WaitForElement.waitUntilElementIsClickable(loginBtn);
        logger.info(CLICK.getMsg());
        loginBtn.click();
    }
}
