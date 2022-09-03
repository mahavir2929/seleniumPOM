package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if(DriverFactory.highlight.equals("true")) {
			jsUtil.flash(element);
		}
		
		return element;

	}

	public String waitForTitleContains(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleValue))) {
			return driver.getTitle();
		}
		return null;
	}

	public WebElement getElement(By locator, int timeout) {
		return doPresenceOfElementLocated(locator, timeout);

	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public List<WebElement> getElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	
	
	
	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetUrl() {
		return driver.getCurrentUrl();
	}
	
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void doClick(By locator) {
		getElement(locator).click();

	}

	public void doClick(By locator, int timeout) {

		doPresenceOfElementLocated(locator, timeout).click();
	}

	public List<String> getElementTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleText = new ArrayList<String>();
		for (WebElement e : eleList) {
			String textlist = e.getText();
			if (!textlist.isEmpty()) {
				eleText.add(textlist);
			}

		}
		return eleText;
	}

	public int getElementCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getAttributeList(By locator, String attributeName) {
		List<WebElement> eleList = getElements(locator);
		List<String> attslist = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attrvalue = e.getAttribute(attributeName);
			attslist.add(attrvalue);
		}

		return attslist;

	}

	public String getAttributeValue(By locator, String attrName) {
		String attrvalue = getElement(locator).getAttribute(attrName);
		System.out.println(attrvalue);
		return attrvalue;
	}

	public boolean verfiyElementpresent(By locator) {
		int elementCount = driver.findElements(locator).size();
		System.out.println("element count: " + elementCount);
		if (elementCount >= 1) {
			System.out.println("element present " + locator);
			return true;
		} else {
			System.out.println("element not present");
			return false;

		}

	}

	/***************** drop down utils */
	public void doDropDownSelectByIndex(By locator, int index) {

		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void doDropDownSelectByVisibleText(By locator, String text) {

		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);

	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));

		List<WebElement> optionslist = select.getOptions();

		for (WebElement e : optionslist) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void selectDropDownnWithoutSelect(By locator, String value) {

		List<WebElement> optionsList = getElements(locator);

		System.out.println(optionsList.size());
		for (WebElement e : optionsList) {

			String text = e.getText();
			if (text.equals(value)) {

				e.click();
				break;
			}

		}

	}

	public void doMoveToElement(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).perform();

	}

	public void doClickChildMenu(By parentmenu, By childmenu) {
		doMoveToElement(parentmenu);
		getElement(childmenu).click();

	}

	/****************** wait utils *****/
	public void doSendKeys(By locator, String value, int timeout) {
		doPresenceOfElementLocated(locator, timeout).sendKeys(value);

	}

	public WebElement doPresenceOfElementLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement isElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public boolean isUrlPresent(String value, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlContains(value));

	}

}
