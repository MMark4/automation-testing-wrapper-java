package saetabis.automation.testingWrapper.services.ui.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="pages")
public class PagesConfig {

    private String baseUrl;

    private String liveTvPath;

    private String offerPath;

    private String loginPath;

    private String logoutPath;
}
