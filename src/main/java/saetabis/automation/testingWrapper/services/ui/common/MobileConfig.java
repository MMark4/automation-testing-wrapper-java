package saetabis.automation.testingWrapper.services.ui.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="mobile")
public class MobileConfig {

    private String apkDir;

    private String apkName;

    private String deviceName;
}
