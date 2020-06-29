package saetabis.automation.testingWrapper.commons;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="logging")
public class LoggingConfig {

    private Boolean isEnabled;

    private Boolean isExtended;

    private Boolean isPrinting;

}
