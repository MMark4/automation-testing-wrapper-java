package saetabis.automation.testingWrapper.services.ui.elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Elements {

    private WebDriver driver;

    private Integer timeout;

    public Elements(Integer _timeout, WebDriver _driver) {

        timeout = _timeout;
        driver = _driver;

        waitForPage();
        PageFactory.initElements(driver, this);
    }

    private void waitForPage() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ( ((JavascriptExecutor) driver).executeScript("return document.readyState")
                .equals("complete"));
        WebDriverWait wait = new WebDriverWait(driver, this.timeout);
        wait.until(pageLoadCondition);

    }
}
