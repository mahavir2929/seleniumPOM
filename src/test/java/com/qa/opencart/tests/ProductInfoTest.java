package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority=1)
	public void getProductHeaderTest() {
		searchResultPage = accountPage.doIsSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");

	}

	@Test(priority=2)
	public void getImagesCountTest() {
		searchResultPage = accountPage.doIsSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getImagesCount(), 4);

	}
    @Test(priority=3)
	public void getProductInfoTest() {
		searchResultPage = accountPage.doIsSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfo = productInfoPage.getProductInfo();
		actProductInfo.forEach((k, v) -> System.out.println(k + ":" + v));
		Assert.assertEquals(actProductInfo.get("name"), "MacBook Pro");

	}

}
