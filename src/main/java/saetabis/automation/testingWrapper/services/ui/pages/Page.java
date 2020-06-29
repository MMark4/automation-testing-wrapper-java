package saetabis.automation.testingWrapper.services.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import saetabis.automation.testingWrapper.services.ui.common.PagesConfig;
import saetabis.automation.testingWrapper.utils.DataReader;

import java.util.concurrent.TimeUnit;

public class Page {

    protected DataReader dataReader;

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected PagesConfig pagesConfig;

    protected FluentWait<WebDriver> fluentWait;

    public Page(WebDriver _driver, String baseUrl, String pathUrl)
    {
        Reporter.log("Navigating to: " + baseUrl + pathUrl,true);
        driver = _driver;
        fluentWait = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        dataReader = new DataReader();
        String url = baseUrl + pathUrl;

        if (!driver.getCurrentUrl().contains(url))
        {
            driver.navigate().to(url);
        }

        wait = new WebDriverWait(driver, 60);
    }

    protected void waitForLoading()
    {
        fluentWait.until(ExpectedConditions.attributeToBe(By.cssSelector("div[data-delay=\"250\"]"),"z-index","100"));
        fluentWait.until(ExpectedConditions.attributeToBe(By.cssSelector("div[data-delay=\"250\"]"),"z-index","0"));
    }

}
