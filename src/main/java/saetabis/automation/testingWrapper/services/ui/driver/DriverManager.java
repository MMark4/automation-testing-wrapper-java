package saetabis.automation.testingWrapper.services.ui.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    protected WebDriver webDriver;
    protected AndroidDriver<AndroidElement> androidDriver;
    protected abstract void initializeDriver();

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
    
    public AndroidDriver<AndroidElement> getAndroidDriver() {
        return androidDriver;
    }
}