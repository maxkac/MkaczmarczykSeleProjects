package generic.assertions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

//Klasa zaimplementowana zgodnie z http://joel-costigliola.github.io/assertj/assertj-assertions-generator.html
public class AssertWebElement extends AbstractAssert<AssertWebElement, WebElement> {

    private Logger logger = LogManager.getLogger(AssertWebElement.class);

    public AssertWebElement(WebElement element) {
        super(element, AssertWebElement.class);
    }

    public static AssertWebElement assertThat(WebElement webElement){
        return new AssertWebElement(webElement);
    }

    //Metoda do sprawdzania czy element jest wyświetlony
    public AssertWebElement isDisplayed(){
        logger.info("Checking if element is displayed");
        isNotNull();

        if(!actual.isDisplayed()){
            failWithMessage("Element was not displayed!");
        }
        logger.info("WebElement was displayed!");
        return this;
    }

    public AssertWebElement isSelected() {
        logger.info("Checking if element is selected.");
        isNotNull();

        if(!actual.isSelected()){
            failWithMessage("Element was not selected!");
        }
        logger.info("WebElement was selected!");
        return this;
    }
    //Metoda do sprawdzania czy element jest wyświetlony
    public AssertWebElement isNotDisplayed(){
        logger.info("Checking if element is displayed");
        isNotNull();

        if(actual.isDisplayed()){
            failWithMessage("Element was displayed!");
        }
        logger.info("WebElement was not displayed!");
        return this;
    }

    //Metoda do sprawdzenia czy element posiada zadany tekst
    public AssertWebElement hasText(String expectedTextValue){
        logger.info("Checking if WebElement has text: " + expectedTextValue);
        isNotNull();

        String actualElementText = actual.getText();
        if(!actualElementText.equals(expectedTextValue)){
            failWithMessage("Element text was <%s> expecting to be <%s>!", actualElementText, expectedTextValue);
        }

        logger.info("WebElement had expected text!");
        return this;
    }

    //Metoda do sprawdzenia czy element NIE posiada zadany tekst
    public AssertWebElement hasNotText(String expectedTextValue){
        logger.info("Checking if WebElement has text: " + expectedTextValue);
        isNotNull();

        String actualElementText = actual.getText();
        if(actualElementText.equals(expectedTextValue)){
            failWithMessage("Element text was <%s> expecting to be not <%s>!", actualElementText, expectedTextValue);
        }

        logger.info("WebElement had expected text!");
        return this;
    }

    //Metoda do sprawdzenia czy element zawiera zadany tekst, ignorując wielkosc znakow
    public AssertWebElement containsText(String expectedTextValue){
        logger.info("Checking if WebElement has text: '{}'", expectedTextValue);
        isNotNull();

        String actualElementText = actual.getText().toLowerCase();
        if(!actualElementText.contains(expectedTextValue.toLowerCase())){
            failWithMessage("Element text was <%s> expecting to be <%s>!", actualElementText, expectedTextValue);
        }

        logger.info("WebElement had expected text!");
        return this;
    }

    public AssertWebElement notContainsText(String expectedTextValue) {
        logger.info("Checking if WebElement has text: " + expectedTextValue);
        isNotNull();

        String actualElementText = actual.getText().toLowerCase();
        if(actualElementText.contains(expectedTextValue.toLowerCase())){
            failWithMessage("Element text was <%s> expecting to be not <%s>!", actualElementText, expectedTextValue);
        }

        logger.info("WebElement had expected text!");
        return this;
    }

    //Metoda do sprawdzenia czy element zawiera zadany tekst, ignorując wielkosc znakow
    //metoda z 2 wariantami (np. tekst w 2 wariantach językowych)
    public AssertWebElement containsText(String expectedTextValue, String expectedTextValue2){
        logger.info("Checking if WebElement has text: " + expectedTextValue);
        isNotNull();

        String actualElementText = actual.getText().toLowerCase();
        if(!actualElementText.contains(expectedTextValue.toLowerCase()) && !actualElementText.contains(expectedTextValue2.toLowerCase())){
            failWithMessage("Element text was <%s> expecting to contains <%s>!", actualElementText, expectedTextValue);
        }

        logger.info("WebElement had expected text!");
        return this;
    }

    //Metoda do sprawdzenia czy element posiada podany atrybut z przekazanyą wartością
    public AssertWebElement hasAttribute(String expectedAttributeValue, String attribute){
        logger.info("Checking if WebElement has text: " + expectedAttributeValue);
        isNotNull();

        String actualElementAttribute = actual.getAttribute(attribute);
        if(!actualElementAttribute.equals(expectedAttributeValue)){
            failWithMessage("Element text was <%s> expecting to be <%s>!", actualElementAttribute, expectedAttributeValue);
        }

        logger.info("WebElement had expected attribute!");
        return this;
    }

    //Metoda do sprawdzenia czy element posiada podany atrybut z przekazanyą wartością
    public AssertWebElement hasNotAttribute(String notExpectedAttributeValue, String attribute){
        logger.info("Checking if WebElement has text: " + notExpectedAttributeValue);
        isNotNull();

        String actualElementAttribute = actual.getAttribute(attribute);
        if(actualElementAttribute.equals(notExpectedAttributeValue)){
            failWithMessage("Element text was <%s> expecting to be not <%s>!", actualElementAttribute, notExpectedAttributeValue);
        }

        logger.info("WebElement had expected attribute!");
        return this;
    }

    public AssertWebElement hasAttributeContains(String expectedAttributeValue, String attribute) {
        logger.info("Checking if WebElement has text: " + expectedAttributeValue);
        isNotNull();

        String actualElementAttribute = actual.getAttribute(attribute);
        if(!actualElementAttribute.contains(expectedAttributeValue)){
            failWithMessage("Element text was <%s> expecting to be <%s>!", actualElementAttribute, expectedAttributeValue);
        }

        logger.info("WebElement had expected attribute!");
        return this;
    }
}
