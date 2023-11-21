package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserCustomerInforPageObject extends BasePage{
	WebDriver driver;

	public UserCustomerInforPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean isCustomerInforPageDisplayed() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
