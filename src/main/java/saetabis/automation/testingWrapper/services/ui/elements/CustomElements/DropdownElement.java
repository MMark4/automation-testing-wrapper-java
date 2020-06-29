package saetabis.automation.testingWrapper.services.ui.elements.CustomElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownElement extends ElementImpl{

    public DropdownElement(WebElement _element) {
        super(_element);
    }

    public void selectOption(String option)
    {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(option);
    }
}
