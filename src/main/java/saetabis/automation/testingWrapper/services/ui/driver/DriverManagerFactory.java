package saetabis.automation.testingWrapper.services.ui.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DriverManagerFactory {

	@Autowired
	@Qualifier("chromeDriverManager")
	private DriverManager chromeDriverManager;

	@Autowired
	@Qualifier("androidDriverManager")
	private DriverManager androidDriverManager;

	public DriverManager getManager(Browser browser) {
		DriverManager driverManager;
		switch (browser) {
		case CH:
			chromeDriverManager.initializeDriver();
			driverManager = chromeDriverManager;
			break;
		case ANDROID:
			androidDriverManager.initializeDriver();
			driverManager = androidDriverManager;
			break;

		default:
			// todo rest of browsers
			driverManager = null;
		}
		return driverManager;
	}
}
