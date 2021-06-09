package com.pos.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;

import com.pos.util.TestUtill;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream("/Users/abhi/Desktop/Javatest/OliverTest/src/main/java/com/pos/config/config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("URL");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/abhi/Desktop/Javatest/driver/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("user-data-dir=/Users/abhi/Desktop/Javatest/user-data-dir");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(TestUtill.IMPLICIT_WAIT, TimeUnit.SECONDS);
		}
		else if(browserName.equals("safari")) {
			driver = new SafariDriver();
		}
		
		driver.get(url);
	}
}
