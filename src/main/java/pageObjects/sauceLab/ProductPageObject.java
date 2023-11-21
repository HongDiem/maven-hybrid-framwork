package pageObjects.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.saiceLab.ProductPageUI;

public class ProductPageObject extends BasePage{
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textITem) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, textITem);
	}

	public boolean isProductSortByAscending() {
		ArrayList<String> productUIList = new ArrayList<String>();
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
			System.out.println("Product Name ở trên UI: " + productName.getText());
		}
		
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		Collections.sort(productSortList);
		for (String productName : productSortList) {
			System.out.println("Product Name sau khi sort: " + productName);
		}
		
		return productSortList.equals(productSortList);
	}

	public boolean isProductSortByDescending() {
		ArrayList<String> productUIList = new ArrayList<String>();
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
			System.out.println("Product Name ở trên UI: " + productName.getText());
		}
		
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		Collections.sort(productSortList);
		for (String productName : productSortList) {
			System.out.println("Product Name sau khi sort ASC: " + productName);
		}
		Collections.reverse(productSortList);
		for (String productName : productSortList) {
			System.out.println("Product Name sau khi sort DESC: " + productName);
		}
		
		return productSortList.equals(productSortList);
	}

	public boolean isPriceSortByAscending() {
		ArrayList<Float> productUIList = new ArrayList<Float>();
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
			System.out.println("Product Price ở trên UI: " + productPrice.getText());
		}
		
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}
		
		Collections.sort(productSortList);
		for (Float productName : productSortList) {
			System.out.println("Product Name sau khi sort: " + productName);
		}
		
		return productSortList.equals(productSortList);
	}

	public boolean isPriceSortByDescending() {
		ArrayList<Float> productUIList = new ArrayList<Float>();
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
			System.out.println("Product Price ở trên UI: " + productPrice.getText());
		}
		
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}
		
		Collections.sort(productSortList);
		for (Float productName : productSortList) {
			System.out.println("Prodcut Name sau khi sort ASC: " + productName);
		}
		
		Collections.sort(productSortList);
		for (Float productName : productSortList) {
			System.out.println("Prodcut Name sau khi sort DESC: " + productName);
		}
		
		return productSortList.equals(productSortList);
	}
	

}
