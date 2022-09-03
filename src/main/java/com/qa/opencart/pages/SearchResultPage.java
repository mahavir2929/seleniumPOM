package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productResults = By.cssSelector("div.caption a");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public int getProductCountList() {

		int countList = eleUtil.getElements(productResults, Constants.DEFAULT_TIME_OUT).size();
		return countList;

	}

	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("main product name is "+ mainProductName);
		List<WebElement> searchList = eleUtil.getElements(productResults, Constants.DEFAULT_TIME_OUT);
		for (WebElement e : searchList) {
			String text = e.getText();
			if (text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);

	}

}
