package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By header = By.cssSelector("div#logo h1 a");
	private By registerLink = By.linkText("Register");
	private By errorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	public String getLoginPageTitle() {
		return eleUtil.waitForTitleContains(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}

	public AccountsPage doLogin(String un, String pwd) {

		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}

//	public boolean doWrongCredentialsLogin(String un, String pwd) {
//		eleUtil.doSendKeys(emailId, un);
//		eleUtil.doSendKeys(password, pwd);
//		eleUtil.doClick(loginBtn);
//
//		String text = eleUtil.doGetText(errorMsg);
//		if (text.contains(Constants.LOGIN_ERROR_MESG)) {
//			System.out.println("login not correct");
//			return false;
//		}
//		return true;
//
//	}
	
	public RegistrationPage navigateRegistration() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	
	
	

}
