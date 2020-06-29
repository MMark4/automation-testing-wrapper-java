package saetabis.automation.testingWrapper;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import saetabis.automation.testingWrapper.commons.MainConfig;

@SpringBootTest(classes = {MainConfig.class})
public class uiTest extends AbstractTestNGSpringContextTests {

}