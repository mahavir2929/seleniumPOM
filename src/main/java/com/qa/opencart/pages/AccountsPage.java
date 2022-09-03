package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	private By accSections = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo h1 a");
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");

	public String getAccountPageTitle() {
		return eleUtil.waitForTitleContains(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccPageUrl() {
		return eleUtil.doGetUrl();
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}

	public SearchResultPage doIsSearch(String productName) {
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}

	public List<String> getAccountSecList() {
		List<WebElement> accSecList = eleUtil.getElements(accSections);
		List<String> valList = new ArrayList<String>();
		for (WebElement e : accSecList) {
			String text = e.getText();
			valList.add(text);
		}
		return valList;

	}

	public void doLogout() {
		if (isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}

}