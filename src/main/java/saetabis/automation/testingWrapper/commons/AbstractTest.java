package saetabis.automation.testingWrapper.commons;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;
import saetabis.automation.testingWrapper.services.api.commons.Services;

import java.io.IOException;

@Log4j
@SpringBootTest(classes = {MainConfig.class})
public class AbstractTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private Services api;

    @Autowired
    private SoftAssert softly;

    public Services api() {
        return api;
    }

    public SoftAssert softly() {
        return softly;
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws IOException {
            setLog4j();
    }

    public static void setLog4j(){
        PropertyConfigurator.configure(AbstractTest.class
                .getClassLoader().getResourceAsStream("log4j.properties"));
    }
}
