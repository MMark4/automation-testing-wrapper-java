package saetabis.automation.testingWrapper.services.api.responses.channelsService;

import lombok.Data;

@Data
public class GuideResponse {

    private Channels[] channels;

    private Categories[] categories;
}
