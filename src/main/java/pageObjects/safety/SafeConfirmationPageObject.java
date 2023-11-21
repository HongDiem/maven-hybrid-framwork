package pageObjects.safety;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class SafeConfirmationPageObject extends BasePage{
	WebDriver driver;

	public SafeConfirmationPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToCheckbox1() {
		waitForElementClickable(driver, pageUI.safety.SafeConfirmationpageUI.CHECKBOX_ONE);
		clickToElement(driver, pageUI.safety.SafeConfirmationpageUI.CHECKBOX_ONE);
	}

	public void inputToEmergencyContactTextbox(String emergencyContact) {
		waitForAllElementVisible(driver, pageUI.safety.SafeConfirmationpageUI.EMERGENCTY_CONTACT_TEXTBOX);
		sendkeyToElement(driver, pageUI.safety.SafeConfirmationpageUI.EMERGENCTY_CONTACT_TEXTBOX, emergencyContact);
	}

	public void inputToSubstituteTextbox(String substitute) {
		waitForAllElementVisible(driver,  pageUI.safety.SafeConfirmationpageUI.SUBSTITUTE_TEXTBOX);
		sendkeyToElement(driver, pageUI.safety.SafeConfirmationpageUI.SUBSTITUTE_TEXTBOX, substitute);
	}

	public DisasterDetailPageObject clickToSubmitButton() {
		waitForElementClickable(driver, pageUI.safety.SafeConfirmationpageUI.SUBMIT_BUTTON);
		clickToElement(driver, pageUI.safety.SafeConfirmationpageUI.SUBMIT_BUTTON);
		return PageGeneratorManager.getDisasterDetailPage(driver);
	}
	

}
