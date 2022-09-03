package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void getAccountPageTitleTest() {
		String actTitle = accountPage.getAccountPageTitle();
		System.out.println("acc page title is: " + actTitle);

		Assert.assertEquals(actTitle,Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void isLogOutLinkExistTest() {
		Assert.assertTrue(accountPage.isLogoutLinkExist());
	}

	@Test(priority = 3)
	public void getAccountSecListTest() {
		List<String> actSectionList = accountPage.getAccountSecList();
		Assert.assertEquals(actSectionList, Constants.expectedSecList());
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "Macbook" }, { "Samsung" }, { "Apple" } };
	}

	@Test(priority = 4, dataProvider = "productData")
	public void doIsSearchTest(String productName) {
		searchResultPage = accountPage.doIsSearch(productName);
		Assert.assertTrue(searchResultPage.getProductCountList() > 0);
	}

	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { { "Macbook" ,"MacBook Pro"}, 
			{ "iMac","iMac" }
			 };
	}
	@Test(priority=5,dataProvider = "productSelectData")
	public void selectProductTest(String productName,String mainProductName) {
		searchResultPage = accountPage.doIsSearch(productName);
		productInfoPage=searchResultPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(),mainProductName);
		

	}

}
