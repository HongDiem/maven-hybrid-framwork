package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshToPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait expDriverWait = new WebDriverWait(driver, longTimeout);
		return expDriverWait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}	
	
	public void switchToWindowByID(WebDriver driver, String windowID) {
			Set<String> allWindowIDs = driver.getWindowHandles();
			for (String id : allWindowIDs) {
				if (!id.equals(windowID)) {
					driver.switchTo().window(id);
					break;
				}
			}
	}
	
	public void switchToWindowByPageTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles(); 
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles(); 
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=") ){
			by = By.id(locatorType.substring(3));
		} else if(locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")){
			by = By.className(locatorType.substring(6));
		}else if(locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")){
			by = By.name(locatorType.substring(5));
		}else if(locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")){
			by = By.cssSelector(locatorType.substring(4));
		}else if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")){
			by = By.xpath(locatorType.substring(6));
		}else {
			throw new RuntimeException("Locator type is not supported");
		}
		
		return by;
	}
	
	private String getDynamicXpath(String locatorType, String...dynamicValues) {
		System.out.println("Locator Type Before = " + locatorType);
		if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
		locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		
		for (String value : dynamicValues) {
			System.out.println("Value map to locator = " + value);
			
		}
		
		System.out.println("Locator Type After = " + locatorType);
		return locatorType;
	}
	
	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String...dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	
	public String getElementText(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String...dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	 public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		 Select select = new Select(getWebElement(driver, locatorType));
		 return select.isMultiple();
	 }
	
	 public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItem) {
		 	getWebElement(driver, parentXpath).click();
			sleepInSecond(1);

			WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
			List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
			for (WebElement item : allItems) {
				if (item.getText().trim().equals(expectedItem)) {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
					item.click();
					sleepInSecond(1);
					break;
				}
			}
		}
	 
	 public void sleepInSecond(long time) {
			try {
				Thread.sleep(time * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName ) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String...dynamicValues ) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	
	public String getHexaColorRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	
	public int getElementSize(WebDriver driver, String locatorType, String...dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locator);
		overrideImplicitTimeout(driver, longTimeout);
		
		if (elements.size() == 0) {
			return true;
		}else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	public void swithToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	public void swithToFrameIframe(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String...dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isImageLoaded(WebDriver driver, String locatorType, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return status;
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementUnDisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}
	
	public void waitForElementInVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}
	
	public void waitForAllElementInVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
		
	
	
	
}