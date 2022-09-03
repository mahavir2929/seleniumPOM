package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void setupRegistration() {
		registrationPage=loginpage.navigateRegistration();
		
	}
	
	@Test
	public void doRegisterTest() {
		Assert.assertTrue(registrationPage.accountRegister("tom","sam","test11@test.lcl","1231231234","test123","yes"));
	}

	
	
}
