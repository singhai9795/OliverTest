package com.pos.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pos.util.TestUtill;
import com.pos.util.WebEventListner;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListner eventListner;
	public static ExtentReports extentReport;
	public static ExtentTest logger;
	
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
		
		e_driver = new EventFiringWebDriver(driver);
		eventListner = new WebEventListner();
		e_driver.register(eventListner);
		driver = e_driver;
		
		
		driver.get(url);

	}
	
	@BeforeTest
	public static void extentReportSetup() {
//		for 4.0.0 version
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/"+ TestUtill.getCurrentDateTime() +"ExtentReportResults.html"));
		extentReport = new ExtentReports();
		extentReport.attachReporter(extent);
		
//		for 5.0.0 version
//		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"ExtentReportResults.html");
//		extent = new ExtentReports();
//		extent.attachReporter(spark);
	}
}
