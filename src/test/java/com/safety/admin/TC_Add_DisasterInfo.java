package com.safety.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.safety.DisasterDetailPageObject;
import pageObjects.safety.DisasterInformationPageObject;
import pageObjects.safety.LoginPageObject;
import pageObjects.safety.PageGeneratorManager;
import pageObjects.safety.SafeConfirmationPageObject;


public class TC_Add_DisasterInfo extends BaseTest{
	private WebDriver driver;
	private LoginPageObject loginPage;
	private DisasterInformationPageObject disasterInforPage;
	private DisasterDetailPageObject disasterDetailPage;
	private SafeConfirmationPageObject safeConfirmationpage;
	
	private String userID, Password, emergencyContact, substitute;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		disasterInforPage = PageGeneratorManager.getDisasterInfoPage(driver);
		
		Password = "jinso_admin";
		userID = "jinso_admin";
		emergencyContact = "automationtest";
		substitute = "auto substitute";
	}
	
	@Test
	public void TC_01_Login_Success() {
		loginPage.inputToUserNameTextbox(userID);
		loginPage.inputToPasswordTextbox(Password);
		disasterInforPage = loginPage.clickToSubmitButton();
		disasterDetailPage = disasterInforPage.clickToTextByButton(driver, "diemvthtest");
		safeConfirmationpage = disasterDetailPage.clickToTextByIDNameButton(driver, "01189");
//		safeConfirmationpage.clickToCheckbox1();
		safeConfirmationpage.inputToEmergencyContactTextbox(emergencyContact);
		safeConfirmationpage.inputToSubstituteTextbox(substitute);
		disasterDetailPage = safeConfirmationpage.clickToSubmitButton();
	}	
	
	
	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}
}
