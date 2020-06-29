package saetabis.automation.testingWrapper.services.ui.elements.CustomElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class AutoCompleteElement extends ElementImpl{

    public AutoCompleteElement(WebElement _element) {
        super(_element);
    }

    public void choose(FluentWait fluentWait, String option)
    {
        element.sendKeys(option);

        WebElement parent = element.findElement(By.xpath("./ancestor::div[contains(concat(' ', normalize-space(@class), ' '), 'geosuggest')]"));
        WebElement optionElement = parent.findElement(By.xpath("//ul/li"));
        fluentWait.until(ExpectedConditions.elementToBeClickable(optionElement));
        optionElement.click();

    }
}
