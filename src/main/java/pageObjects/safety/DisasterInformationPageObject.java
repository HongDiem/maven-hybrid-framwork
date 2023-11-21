package pageObjects.safety;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DisasterInformationPageObject extends BasePage {
	WebDriver driver;

	public DisasterInformationPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean isDisasterDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public DisasterDetailPageObject clickToTextByButton(WebDriver driver, String nameofRow) {
		waitForElementClickable(driver, pageUI.safety.DisasterInforPageUI.LIST_EMPLOYEE_BUTTON, nameofRow);
		clickToElement(driver, pageUI.safety.DisasterInforPageUI.LIST_EMPLOYEE_BUTTON, nameofRow);
		return PageGeneratorManager.getDisasterDetailPage(driver);
	}
	
	
}
