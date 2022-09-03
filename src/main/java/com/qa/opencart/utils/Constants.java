package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	
	public static final String LOGIN_PAGE_TITLE="Account Login11";
	public static final int DEFAULT_TIME_OUT=10;
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final String LOGIN_ERROR_MESG ="Warning: No match for E-Mail Address and/or Password.";
	public static final String SUCCESS_MESSAGE = "Your Account Has Been Created!";

	
	
	
	public static List<String> expectedSecList() {
		List <String> expectedList=new ArrayList<String>();
		expectedList.add("My Account");
		expectedList.add("My Orders");
		expectedList.add("My Affiliate Account");
		expectedList.add("Newsletter");
		return expectedList;
	}
	
	
}
