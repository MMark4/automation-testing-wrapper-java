package saetabis.automation.testingWrapper.services.ui.common;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@ComponentScan("tmp")
public class DriverConfig {

    @Value("${mobile.remoteAdress}")
    private String remoteAdress;

    @Value("${webApp.chromeDriver.dir}")
    private String chromeDriverDir;

    @Bean
    public AndroidDriver androidDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException {

        return new AndroidDriver(new URL(remoteAdress), desiredCapabilities);
    }

    @Bean
    public ChromeDriver chromeDriver(DesiredCapabilities desiredCapabilities) {

        System.setProperty("webdriver.chrome.driver", chromeDriverDir);
        ChromeDriver chromeDriver = new ChromeDriver(desiredCapabilities);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    @Bean
    public DesiredCapabilities desiredCapabilities() {
        return new DesiredCapabilities();
    }

    @Bean
    public ChromeOptions chromeOptions() {
        return new ChromeOptions();
    }

    @Bean
    public LoggingPreferences loggingPreferences() {
        return new LoggingPreferences();
    }
}
