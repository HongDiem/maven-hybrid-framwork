package pageObjects.safety;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.safety.LoginPageUI;


public class LoginPageObject extends BasePage{
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputToUserNameTextbox(String userName) {
		waitForAllElementVisible(driver, pageUI.safety.LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver,pageUI.safety.LoginPageUI.USERNAME_TEXTBOX , userName);		
	}

	public void inputToPasswordTextbox(String password) {
		waitForAllElementVisible(driver, pageUI.safety.LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, pageUI.safety.LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public DisasterInformationPageObject clickToLoginButton() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getErrorMessageUserNameNotNull() {
		waitForAllElementVisible(driver, pageUI.safety.LoginPageUI.MESSAGE_ERROR);
		return getElementText(driver, LoginPageUI.MESSAGE_ERROR);
	}

	public DisasterInformationPageObject clickToSubmitButton() {
		waitForElementClickable(driver, pageUI.safety.LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, pageUI.safety.LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getDisasterInfoPage(driver);
	}

	public void clickToCancelPopup() {
		waitForElementClickable(driver, pageUI.safety.LoginPageUI.CANCEL_POPUP);
		clickToElement(driver, pageUI.safety.LoginPageUI.CANCEL_POPUP);
		sleepInSecond(1000);
	}
	
	

}
