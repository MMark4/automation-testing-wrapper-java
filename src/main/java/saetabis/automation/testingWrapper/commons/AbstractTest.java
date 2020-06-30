package saetabis.automation.testingWrapper.commons;

import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import saetabis.automation.testingWrapper.services.api.commons.Services;
import saetabis.automation.testingWrapper.services.ui.common.PagesConfig;
import saetabis.automation.testingWrapper.services.ui.common.WebUser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@Log4j
@SpringBootTest(classes = {MainConfig.class})
public class AbstractTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private Services api;

    @Autowired
    private SoftAssert softly;

    @Autowired
    private WebUser web;

    @Autowired
    protected PagesConfig pagesConfig;

    public Services api() {
        return api;
    }

    public WebUser web() {
      if (web.getDriver() == null) {
            web.build();
        }
        return web;
    }

    public SoftAssert softly() {
        return softly;
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws IOException {
            setLog4j();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, Object[] objects) throws IOException {
        String methodName = method.getName();
        log.info("Start of test : " + method.getName());
        String startTime = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());

    }

    @AfterMethod(alwaysRun = true)
    public final void afterMethod(final ITestResult testResult) {

        if (web != null) {
            web.killBrowser();
            web = null;
        }
    }

    public static void setLog4j(){
        PropertyConfigurator.configure(AbstractTest.class
                .getClassLoader().getResourceAsStream("log4j.properties"));
    }
}
