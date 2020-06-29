package saetabis.automation.testingWrapper.services.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Reporter;
import saetabis.automation.testingWrapper.services.api.commons.EndpointsConfig;
import saetabis.automation.testingWrapper.services.api.commons.Services;
import saetabis.automation.testingWrapper.services.api.responses.GuideResponse;

import static com.google.common.base.Preconditions.checkArgument;
import static io.restassured.RestAssured.given;

public class ChannelsService {

    private final Services api;

    public ChannelsService(Services services) {
        this.api = services;
    }

    public Services getGuide(String a0,String a1,String a2,String a3,String a4,String a5,String a6,String a7,String a8,String a9) {

        checkArgument(a0!=null,"cant be null");
        return api
                .setContentType(ContentType.JSON)
                .setRequestUrl("/v1/guide?start=%s&stop=%s&deviceId=%s&deviceMake=%s&deviceType=%s&deviceVersion=%s&DNT=%s&sid=%s&appName=%s&appVersion=%s",a0,a1,a2,a3,a4,a5,a6,a7,a8,a9)
                .getRequest(200);
    }

}
