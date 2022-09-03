package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("browser name is " + browserName);
		optionsManager = new OptionsManager(prop);

		highlight = prop.getProperty("highlight");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("enter correct browser name");
		}

		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {

		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		try {
			if (envName == null) {
				System.out.println("running on prod env");
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} else {
				System.out.println("Running on Environment: " + envName);
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;

				default:
					System.out.println("No ENV found.....");
					throw new Exception("NOENVFOUNDEXCEPTION");

				}

			}
		} catch (FileNotFoundException e) {
			// log.error("file not found exception is coming.....");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			prop = new Properties();
			prop.load(ip);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}


}