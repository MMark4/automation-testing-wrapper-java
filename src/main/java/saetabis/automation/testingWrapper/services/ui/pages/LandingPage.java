package saetabis.automation.testingWrapper.services.ui.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage{

    @FindBy(id = "root")
    WebElement root;

    public LandingPage(WebDriver driver) {
        super(driver);
        if(driver instanceof AppiumDriver) {
            changeDriverContextToWeb(driver);
        }
    }
}
