package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private Map<String, String> productInfoMap;

	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productIamges = By.xpath("(//div[@id='content']//ul)[1]/li");
	private By productMetaData = By.xpath("(//div[@id='content']//ul)[3]/li");
	private By productMetaPrice = By.xpath("(//div[@id='content']//ul)[4]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getProductHeader() {
		return eleUtil.doGetText(productHeader);
	}

	public int getImagesCount() {
		return eleUtil.getElements(productIamges, 10).size();
	}

	public Map <String,String> getProductInfo() {

		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name",getProductHeader());
		getProductMetaData();
		getProductMetaPrice();
        return productInfoMap;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);

		}

	}

//	$2,000.00
//	Ex Tax: $2,000.00
	private void getProductMetaPrice() {
		List<WebElement> metaPriceList = eleUtil.getElements(productMetaPrice);
		String price = metaPriceList.get(0).getText();
		String exPrice = metaPriceList.get(1).getText();
		productInfoMap.put("price", price);
		productInfoMap.put("extaxPrice", exPrice);

	}

}
