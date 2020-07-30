package pages.mfinans;

import drivers.manager.DriverManager;
import drivers.manager.DriverUtils;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import waits.WaitForElement;

import java.util.List;
import java.util.stream.Collectors;

import static generic.LoggerStrings.CLICK;

public class IndexPage extends BasePage {

    private Logger logger = LogManager.getLogger(IndexPage.class);

    @Step("Visit all links from navigation top bar, check if urls are different.")
    public IndexPage openAllLinksFromNavi() {
        List<WebElement> linksList = naviTopBar.findElements(By.cssSelector("a"));
        logger.info("Founds elements, all add to list (size = {})", linksList.size());
        for (int i = 0; i < linksList.size(); i++) {
            WaitForElement.waitUntilElementIsClickable(linksList.get(i));
            logger.info(CLICK.getMsg());
            linksList.get(i).click();
            String currentUrl = DriverManager.getWebDriver().getCurrentUrl();
            logger.info("Get current url: {}", currentUrl);
            WaitForElement.waitUntilElementIsVisibleRefreshed(body);
            DriverUtils.navigateToPage("http://mfinans.pl/");
            logger.info("Back to main page.");
            linksList = naviTopBar.findElements(By.cssSelector("a"));
            if (i != 2) { //2 a == main page
                Assert.assertNotEquals(currentUrl, DriverManager.getWebDriver().getCurrentUrl());
                logger.info("Compare two urls, before and now; urls are different. (before: {}; now: {})",
                        currentUrl, DriverManager.getWebDriver().getCurrentUrl());
            }
        }
    return this;
    }

    @Step("Open all links from side menu (all kind of loan).")
    public IndexPage openAllLinksFromContent() {
        List<WebElement> linksList = listWithLoanKind.findElements(By.cssSelector("a"));
        List<String> loanKindsStrings = linksList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        logger.info("Founds elements, all add to list (size = {})", linksList.size());
        loanKindsStrings.forEach(k -> {
            String currentUrl = DriverManager.getWebDriver().getCurrentUrl();
            logger.info("Get current url: {}", currentUrl);
            listWithLoanKind.findElements(By.cssSelector("a")).stream().filter(e -> e.getText().equals(k)).findFirst().get().click();
            Assert.assertNotEquals(currentUrl, DriverManager.getWebDriver().getCurrentUrl());
            logger.info("Compare two urls, before and now; urls are different. (before: {}; now: {})",
                    currentUrl, DriverManager.getWebDriver().getCurrentUrl());
        });

        return this;
    }
}
