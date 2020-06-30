package saetabis.automation.testingWrapper;

import org.testng.annotations.Test;
import saetabis.automation.testingWrapper.commons.AbstractTest;
import saetabis.automation.testingWrapper.services.api.responses.channelsService.GuideResponse;

public class apiTest extends AbstractTest {

    @Test
    public void testGuideEndpoint () {

        GuideResponse response = api()
                .getChannelsService()
                .getGuide("2020-06-29T16:00:00-03:00","2020-06-29T20:00:00-03:00","88ba8682-6317-45d0-8465-2053bbd792d5","Chrome","web","83.0.4103.116","0","ce6e3c30-516e-4070-9697-48e01db6cfb4","web","5.5.0-cb4af9777b8de5e8069b2df91dfb4abca377a5e9");


    }
 }
