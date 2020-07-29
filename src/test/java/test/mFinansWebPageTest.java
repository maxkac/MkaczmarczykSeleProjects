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
import pages.mfinans.*;

import java.io.File;
import java.util.Properties;

/**
 * Testy samodzielnie wykonanej strony 'mfinans.pl'. Strona powstała w celu wykorzystywania jej marketingowo dla lokalnej
 * firmy. Ma ona spełniać 2 podstawowe funkje: wizytówka w sieci + prosty formularz kontaktowy dla klienta.
 * Testy tej strony mają na celu ukazanie moich zdolności posługiwania się biblioteką Selenium jak również programowania
 * w Javie. Selenium uczyłem się (i nadal uczę) samodzielnie, natomiast piersze kroki w programowaniu w Javie stawiałem
 * w szkole programowania SDA jak i na studiach.
 * */
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
                .elementIsClickable(By.cssSelector("div.google-maps-link a"))
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
                .elementIsClickable(By.cssSelector("i.icon-mail"))
                .elementIsVisible(By.id("contact"))
                .titleIs("Kontakt");
    }

    @Test
    public void companyShouldHaveThreeLinksInSideMenuHaveContactFormCompareTxtFileTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/kredytFirm.html");
        CompanyPage companyPage = new CompanyPage();
        companyPage
                .pageContains("Kredyt hipoteczny")
                .elementIsClickable(By.cssSelector("#menu-kredyty li:nth-child(1) > a"))
                .pageContains("Kredyt gotówkowy")
                .elementIsClickable(By.cssSelector("#menu-kredyty li:nth-child(2) > a"))
                .pageContains("Kredyt firmowy")
                .elementIsClickable(By.cssSelector("#menu-kredyty li:nth-child(3) > a"))
                .elementIsVisible(By.id("contact"))
                .titleIs("Kredyt gotówkowy")
                .compareTxtWithTxtFile(new File("resources\\KFirmowyTXT.txt"));
    }

    @Test
    public void loanPageShouldHaveTreeLinksInSideMenuHaveContactFormCompareFileTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/index.html");
        BasePage basePage = new BasePage();
        basePage
                .pageContains("Kredyt hipoteczny")
                .elementIsClickable(By.cssSelector("#menu-kredyty li:nth-child(1) > a"))
                .pageContains("Kredyt gotówkowy")
                .elementIsClickable(By.cssSelector("#menu-kredyty li:nth-child(2) > a"))
                .pageContains("Kredyt firmowy")
                .elementIsClickable(By.cssSelector("#menu-kredyty li:nth-child(3) > a"))
                .elementContainsText(  By.className("wrapper"),
                            "RATA OD 412 ZŁ",
                                "za każde 100 tys. zł kredytu , RRSO 4,31%",
                                "DO 35 LAT",
                                "NISKI WKŁAD WŁASNY",
                                "tylko 10 %",
                                "Okres kredytowania")
                .elementIsVisible(By.id("contact"))
                .titleIs("Kredyt hipoteczny")
                .compareTxtWithTxtFile(new File("resources\\KHipoTXT.txt"));
    }

    @Test
    public void aboutPageShouldHaveFiveImgInGalleryAndOneImgInContentTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/aboutMe.html");
        AboutPage aboutPage = new AboutPage();
        aboutPage
                .checkCountOfImgsInGallery()
                .clickAndExitAllImgs();
    }

    @Test
    public void clickImgAndCheckTitleTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/aboutMe.html");
        AboutPage aboutPage = new AboutPage();
        aboutPage
                .clickImgDetailsContains(3, "Certyfikat - EFEKTYWNOŚĆ DORADCY FINANSOWEGO - NOTUS Doradcy Finansowi")
                .clickImgDetailsContains(5, "Certyfikat ukończenia szkolenia bankowego - Unilinkcash")
                .clickImgDetailsContains(1, "Zaświadczenie o ukończeniu kursu - NOTUS Finanse");
    }

    @Test
    public void fillFullPositiveFormContactTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/aboutMe.html");
        requestForContact();
    }

    private void requestForContact() {
        ContactFormPage formPage = new ContactFormPage();
        formPage
                .typeName("Test")
                .typeMail("test@test.te")
                .typePhone("600600600")
                .clickAllAgree()
                .clickSendContact()
                .titleIs("Kredyt hipoteczny");
    }

    @Test
    public void readAllAgreeTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/kredytFirm.html");
        ContactFormPage formPage = new ContactFormPage();
        formPage
                .showAllInfo()
                .compareTxtWithTxtFile(new File("resources\\agrees.txt"));
    }

    @Test
    public void trySendOnlyNameAndPhoneShouldViewAgreementInfoTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/kredytFirm.html");
        ContactFormPage formPage = new ContactFormPage();
        formPage
                .typeName("Test")
                .typePhone("600600600")
                .clickSendContact()
                .pageContains("Nie zaznaczono wszytskich zgód.")
                .pageContains("Bardzo proszę o zaznaczenie wszystkich zgód przy wysyłaniu prośby o kontakt.")
                .elementIsClickable(By.cssSelector("a"));
    }

    @Test
    public void trySendWithoutMailWithAllAgreesShouldViewMailValidationTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/kredytFirm.html");
        ContactFormPage formPage = new ContactFormPage();
        formPage
                .typeName("Test")
                .typePhone("600600600")
                .clickAllAgree()
                .clickSendContact()
                .pageContains("Niepoprawny format adresu e-mail.")
                .pageContains("Proszę podać prawidłowy, np. mail123@vp.pl")
                .elementIsClickable(By.cssSelector("a"));
    }

    @Test
    public void trySendWithInvalidMailShouldViewMailValidationTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/kredytFirm.html");
        ContactFormPage formPage = new ContactFormPage();
        formPage
                .typeName("Test")
                .typePhone("600600600")
                .typeMail("aaaA")
                .clickAllAgree()
                .clickSendContact()
                .pageContains("Niepoprawny format adresu e-mail.")
                .pageContains("Proszę podać prawidłowy, np. mail123@vp.pl")
                .elementIsClickable(By.cssSelector("a"));
    }

    @Test
    public void showAllAgreesAndCloseTest(){
        DriverUtils.navigateToPage("http://mfinans.pl/kredytFirm.html");
        ContactFormPage formPage = new ContactFormPage();
        formPage
                .showAllInfo()
                .closeAllInfo();
    }

    @Test
    public void typeAllInputAndFirstAgreeShouldViewMainPageTest(){
        /*only rodo agree is obligatory to send msg
         */
        ContactFormPage formPage = new ContactFormPage();
        formPage
                .typeName("Test")
                .typePhone("600600600")
                .typeMail("mail123@vp.pl")
                .clickAgree(1)
                .clickSendContact()
                .titleIs("Kredyt hipoteczny");
    }

    @Test
    public void typeAllInputAndAllAgreesButNotFirstShouldViewAgreementInfoTest(){
        ContactFormPage formPage = new ContactFormPage();
        formPage
                .typeName("Test")
                .typePhone("600600600")
                .typeMail("mail123@vp.pl")
                .clickAgree(2)
                .clickAgree(3)
                .clickAgree(4)
                .clickSendContact()
                .pageContains("Nie zaznaczono wszytskich zgód.")
                .pageContains("Bardzo proszę o zaznaczenie wszystkich zgód przy wysyłaniu prośby o kontakt.")
                .elementIsClickable(By.cssSelector("a"));
    }
}
