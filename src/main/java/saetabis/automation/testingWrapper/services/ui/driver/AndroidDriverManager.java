package saetabis.automation.testingWrapper.services.ui.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saetabis.automation.testingWrapper.services.ui.common.MobileConfig;

import java.io.File;

@Component
public class AndroidDriverManager extends DriverManager {

	@Autowired
	private DesiredCapabilities desiredCapabilities;

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private LoggingPreferences loggingPreferences;

	@Autowired
	private MobileConfig mobileConfig;

	public void initializeDriver() {

		File file = new File(mobileConfig.getApkDir());
		File fs = new File(file, mobileConfig.getApkName());

		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mobileConfig.getDeviceName());
		desiredCapabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());

		androidDriver =  beanFactory.getBean(AndroidDriver.class, desiredCapabilities);
	}
}