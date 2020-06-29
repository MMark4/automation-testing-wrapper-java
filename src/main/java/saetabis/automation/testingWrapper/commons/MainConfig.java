package saetabis.automation.testingWrapper.commons;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "saetabis.automation.testingWrapper")
@EnableConfigurationProperties
public class MainConfig {

}
