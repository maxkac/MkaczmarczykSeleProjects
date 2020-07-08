package test;

import configuration.ConfigurationProperties;
import configuration.PropertiesLoader;
import drivers.BrowserType;
import drivers.manager.DriverManager;
import drivers.manager.DriverUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import pages.mfinans.ContactPage;
import pages.mfinans.IndexPage;

import java.util.Properties;

public class mFinansWebPageTest {

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(@Optional BrowserType browserType) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties propertiesFromFile = propertiesLoader.getPropertiesFromFile("configuration.properties");
        ConfigurationProperties.setProperties(propertiesFromFile);

        DriverManager.setWebDriver(browserType);
        DriverManager.getWebDriver();
        DriverUtils.setInitialConfiguration();

        DriverUtils.navigateToPage("http://mfinans.pl/");
    }

    @Step("Close driver.")
    @AfterClass
    public void afterClass(){
        DriverManager.disposeDriver();
    }

    @Description("Open all pages from main page.")
    @Test
    public void goToAllLinksFromIndexTest(){
        IndexPage indexPage = new IndexPage();
        indexPage
                .openAllLinksFromNavi()
                .openAllLinksFromContent();
    }

    @Description("Open Contact Page, switch to map frame, check visibility of elements on map.")
    @Test
    public void mapShouldBeVisibleContainsTextAndHaveClickableFullViewLinkTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/kontakt.html");
        ContactPage contactPage = new ContactPage();
        contactPage
                .mapIsVisible()
                .switchToFrame()
                .pageContains("Map data ©2020 Google")
                .pageContains("Terms of Use")
                .pageContains("Report a map error")
                .pageContains("View larger map")
                .elementIsClickable(By.cssSelector("a.google-maps-link"))
                .switchToParentFrame();
    }

    @Description("Checking if the Contact page contains the text (phone numbers, address and mail) and whether the " +
            "phone numbers and mail are clickable.")
    @Test
    public void contactPageShouldHaveTwoPhonesNumberMailAndAddress(){
        DriverUtils.navigateToPage("http://mfinans.pl/kontakt.html");
        ContactPage contactPage = new ContactPage();
        contactPage
                .pageContains(  "785 660 754",
                                "603 390 909",
                                "maciej.kaczmarczyk@notus.pl",
                                "ul. Poniatowskiego 6/33",
                                "26-600 Radom")
                .elementIsClickable(By.cssSelector("i.icon-phone"))
                .elementIsClickable(By.cssSelector("i.icon-mail"));
    }
}
