package saetabis.automation.testingWrapper.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.api.*;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class SoftAssert extends SoftAssertions {

    @Autowired
    private LoggingConfig loggingConfig;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static SoftAssert softly = new SoftAssert();
    public static boolean print_logs = true;

    private SoftAssert() {
    }

    public static SoftAssert getInstance(WebDriver drv) {
        driver.set(drv);
        return getInstance();
    }

    public static SoftAssert getInstance() {
        return softly;
    }

    public StringAssert assertThat(String actual) {
        // Take Screenshot here
        if (this.print_logs) {
            // Take Screenshot here
            if (loggingConfig.getIsExtended()) {
                log.info(String.format("Validating the value: %s", actual));
            }
        }
        return softly.proxy(StringAssert.class, String.class, actual);
    }

    public BooleanAssert assertThat(boolean actual) {
        if (this.print_logs) {
            if (loggingConfig.getIsExtended()) {
                log.info(String.format("Validating the value: %s", actual));
            }
        }
        return softly.proxy(BooleanAssert.class, Boolean.class, actual);
    }

    public <T> ProxyableListAssert<T> assertThat(List<? extends T> actual) {
        if (this.print_logs) {
            if (loggingConfig.getIsExtended()) {
                log.info(String.format("Validating the value: %s", ArrayUtils.toString(actual)));

            }
        }
        return (ProxyableListAssert) softly.proxy(ProxyableListAssert.class, List.class, actual);
    }

    public IntegerAssert assertThat(int actual) {
        if (this.print_logs) {
            if (loggingConfig.getIsExtended()) {
                log.info(String.format("Validating the value: %s", actual));
            }
        }
        return softly.proxy(IntegerAssert.class, Integer.class, actual);
    }

    public LongAssert assertThat(Long actual) {
        if (this.print_logs) {
            if (loggingConfig.getIsExtended()) {
                log.info(String.format("Validating the value: %s", actual));
            }
        }
        return softly.proxy(LongAssert.class, Long.class, actual);
    }

    public void reportAll() {
        List<Throwable> collectedErrors = softly.errorsCollected();
        List<String> formattedErrors = new ArrayList<>();
        if (collectedErrors != null && !collectedErrors.isEmpty()) {
            List<String> loggingErrors = new ArrayList<>();
            for (Throwable th : collectedErrors) {
                ObjectMapper mapper = new ObjectMapper();
                String stLog = null;
                try {
                    stLog = mapper.writeValueAsString(th);
                    StringBuilder stBuild = new StringBuilder();
                    stBuild.append(th.getMessage());
                    StackTraceElement[] stElements = th.getStackTrace();
                    for (int i = 0; i < 7; i++) {
                        stBuild.append(System.lineSeparator());
                        stBuild.append(stElements[i].toString());
                    }
                    formattedErrors.add(stBuild.toString());

                } catch (JsonProcessingException e) {
                    log.info("Failed to serialize soft assertion exception");
                }
                loggingErrors.add(stLog);
                log.error(stLog);
            }
            log.error(new SoftAssertionError(formattedErrors));
            softly = new SoftAssert();
            throw new SoftAssertionError(loggingErrors);
        }
    }
}