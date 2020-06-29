package saetabis.automation.testingWrapper.services.api.commons;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="endpoints")
public class EndpointsConfig {

    private String channelsUrl;

    private String port;

    private String guideEndpoint;
}
