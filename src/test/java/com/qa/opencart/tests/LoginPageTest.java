package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void getLoginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);

	}

	@Test(priority = 2)
	public void isForgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}

	@Test(priority = 3)
	public void doLoginTest() {
		accountPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountPage.getAccountPageTitle(),Constants.ACCOUNT_PAGE_TITLE);

	}

}
