package saetabis.automation.testingWrapper.services.ui.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
public class ChromeDriverManager extends DriverManager {

    @Autowired
    private DesiredCapabilities desiredCapabilities;

    @Autowired
    private ChromeOptions chromeOptions;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private LoggingPreferences loggingPreferences;


    protected void initializeDriver() {
        desiredCapabilities = DesiredCapabilities.chrome();
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("-incognito");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
        webDriver = beanFactory.getBean(ChromeDriver.class, desiredCapabilities);
    }
}
