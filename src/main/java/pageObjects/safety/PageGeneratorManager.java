package pageObjects.safety;

import org.openqa.selenium.WebDriver;





public class PageGeneratorManager {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static CreateDisasterPageObject getCreateDisasterPage(WebDriver driver) {
		return new CreateDisasterPageObject(driver);
	}
	
	public static DisasterInformationPageObject getDisasterInfoPage(WebDriver driver) {
		return new DisasterInformationPageObject(driver);
	}
	
	public static DisasterDetailPageObject getDisasterDetailPage(WebDriver driver) {
		return new DisasterDetailPageObject(driver);
	}
	
	public static SafeConfirmationPageObject getSafeConfirPage(WebDriver driver) {
		return new SafeConfirmationPageObject(driver);
	}
	
}
