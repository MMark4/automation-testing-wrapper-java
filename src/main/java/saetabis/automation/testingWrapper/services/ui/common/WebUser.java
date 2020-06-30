package saetabis.automation.testingWrapper.services.ui.common;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import saetabis.automation.testingWrapper.services.ui.driver.Browser;
import saetabis.automation.testingWrapper.services.ui.driver.DriverManager;
import saetabis.automation.testingWrapper.services.ui.driver.DriverManagerFactory;
import saetabis.automation.testingWrapper.services.ui.pages.BasePage;

import java.lang.reflect.InvocationTargetException;

import static com.google.common.base.Preconditions.checkArgument;

@Log4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WebUser {

    @Autowired
    private DriverManagerFactory driverManagerFactory;

    private DriverManager driverManager;

    private WebDriver driver;

    public void build() {
        driverManager = driverManagerFactory.getManager(Browser.CH);
        driver = driverManager.getWebDriver();
        logBrowserDetails();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void removeDriver() {
        driverManager.quitDriver();
    }

    public void logBrowserDetails() {
        log.info("------------browser details---------------");
        log.info("UserAgent : " + ((JavascriptExecutor) getDriver()).executeScript("return navigator.userAgent;"));

        Capabilities caps = ((RemoteWebDriver) getDriver()).getCapabilities();

        log.info("Platform : " + caps.getPlatform());
        log.info("Browser : " + caps.getBrowserName());
        log.info("Browser Version : " + caps.getVersion());
    }

    public WebUser navigateTo(String server) {
        log.info("Navigate to - " + server);
        getDriver().navigate().to(server);
        if (isServerDown()) {
            killBrowser();
            throw new RuntimeException("Unable to open the " + getDriver().getCurrentUrl() + "; either the server is down or incorrect url");
        }
        return this;
    }

    private boolean isServerDown() {
        String htmlSource = getDriver().getPageSource().toLowerCase();
        return htmlSource.contains("The connection was reset") ||
                htmlSource.contains("This webpage is not availabe") || htmlSource.contains("server not found");
    }

    public void killBrowser() {
        try {
            if (getDriver() != null) getDriver().quit();
            driver = null;
            // Commented since I couldnt understand the purpose, this causes chrome closure for multi threaded tests
            //DriverFactory.closeDriver(); //shall also close the driver since we kill the browser otherwise calling mobile() will have the wrong driver.
        } catch (Exception e) {
            log.info("Exception while quitting the driver and reason could be a driver timeout",e);
        }
    }

    /**
     * Use navigateToPage method for classes that are annotated with PageProperties
     */
    public  <T extends BasePage> T getPage(Class<T> page) {
        if (page == null) {
            return null;
        }
        try {
            log.info("Current URL: " + getDriver().getCurrentUrl() + " Corresponding Page Object: " + page.getSimpleName());
            return page.getConstructor(WebDriver.class).newInstance(getDriver());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ResourceNotFoundException("Couldn't instantiate object from class: " + page.getSimpleName() + ", current URL: " + getDriver().getCurrentUrl(), e);
        }
    }

    public <T extends BasePage> T navigateToPage(String url, Class<T> page) {
        checkArgument(url != null && !url.isEmpty(), "URL cant be null or empty");
        checkArgument(page != null, "next page cant be null");
        navigateTo(url);
        return getPage(page);
    }

    public String getBrowserTimeZone() {
        return ((JavascriptExecutor) getDriver()).executeScript("return Intl.DateTimeFormat().resolvedOptions().timeZone").toString();
    }
}
