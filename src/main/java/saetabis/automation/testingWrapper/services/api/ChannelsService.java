package saetabis.automation.testingWrapper.services.api;

import io.restassured.http.ContentType;
import saetabis.automation.testingWrapper.services.api.commons.Services;
import saetabis.automation.testingWrapper.services.api.responses.channelsService.GuideResponse;

import static com.google.common.base.Preconditions.checkArgument;

public class ChannelsService {

    private final Services api;

    public ChannelsService(Services services) {
        this.api = services;
    }

    public GuideResponse getGuide(String startTime,String endTime,String deviceId,String deviceMake,String deviceType,String deviceVersion,String dnt,String sid,String appName,String appVersion) {

        checkArgument(startTime!=null && endTime!=null && deviceId!=null && deviceMake!=null && deviceType!=null && deviceVersion!=null && dnt!=null && sid!=null && appName!=null && appVersion!=null,"Parameters cant be null");
        return api
                .setContentType(ContentType.JSON)
                .setRequestUrl("/v1/guide?start=%s&stop=%s&deviceId=%s&deviceMake=%s&deviceType=%s&deviceVersion=%s&DNT=%s&sid=%s&appName=%s&appVersion=%s",startTime,endTime,deviceId,deviceMake,deviceType,deviceVersion,dnt,sid,appName,appVersion)
                .getRequest(200)
                .getResponseAsObject(GuideResponse.class);
    }

}
