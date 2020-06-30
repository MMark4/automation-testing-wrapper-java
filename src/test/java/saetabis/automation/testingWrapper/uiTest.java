package saetabis.automation.testingWrapper;

import org.testng.annotations.Test;
import saetabis.automation.testingWrapper.commons.AbstractTest;
import saetabis.automation.testingWrapper.services.ui.pages.LandingPage;

public class uiTest extends AbstractTest {

    @Test
    public void testGuideGrid () {
            web()
                .navigateToPage(pagesConfig.getLanding(), LandingPage.class);
    }
}