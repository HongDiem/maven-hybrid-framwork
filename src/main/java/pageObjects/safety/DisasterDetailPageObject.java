package pageObjects.safety;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class DisasterDetailPageObject extends BasePage {
	WebDriver driver;

	public DisasterDetailPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public SafeConfirmationPageObject clickToTextByIDNameButton(WebDriver driver, String IDName) {
		waitForElementClickable(driver, pageUI.safety.SafeConfirmationpageUI.DETAI_BUTTON, IDName);
		clickToElement(driver, pageUI.safety.SafeConfirmationpageUI.DETAI_BUTTON, IDName);
		return PageGeneratorManager.getSafeConfirPage(driver);
	}
	

}
