package saetabis.automation.testingWrapper.services.api.commons;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import saetabis.automation.testingWrapper.commons.LoggingConfig;
import saetabis.automation.testingWrapper.services.api.ChannelsService;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RedirectConfig.redirectConfig;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Services extends RestCalls {

    @Autowired
    private EndpointsConfig endpointsConfig;

    private ChannelsService channelsService;

    public Services() {
        setPrintStream();
        RestAssuredConfig conf = restAssuredConfig();
        conf = conf.redirect(redirectConfig().followRedirects(false));
        conf = conf.encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

        RestAssured.config = conf;
        requestSpecification = RestAssured.given();
        setContentType(ContentType.JSON);
    }

    public ChannelsService getChannelsService() {
        if (channelsService == null) channelsService = new ChannelsService(this);
        resetHeaders();
        baseUrl = endpointsConfig.getChannelsUrl();
        return channelsService;
    }
}
