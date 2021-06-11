package com.pos.testcases;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pos.base.TestBase;
import com.pos.pages.LoginPage;
import com.pos.pages.SiteSelectionPage;
import com.pos.util.TestUtill;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	SiteSelectionPage siteSelectionPage;
	
	LoginPageTest(){
		super();
	}
	
//	@BeforeSuite
//	public void extentReport() {
//		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/"+ System.currentTimeMillis()+"ExtentReportResults.html"));
//		extentReport = new ExtentReports();
//		extentReport.attachReporter(extent);
//	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		logger = extentReport.createTest("loginPageTitleTest");
		logger.log(Status.PASS, "Looking for title");
		String pageTitle = loginPage.getPageTitle();
		Assert.assertEquals(pageTitle, "Oliver POSs");
//		if(pageTitle.equals("Oliver POSs")) {
//			logger.pass("Pass");
//		}
//		else {
//			logger.fail("Fail");
//		}
	}
	
	@Test
	public void blankUsernameTest() {
		logger = extentReport.createTest("blankUsernameTest");
		loginPage.login("", prop.getProperty("paasword"));
		String currentUrl = loginPage.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://dev1.sell.olivertest.com/login","Moved to site selection page");
//		if(currentUrl.equals("https://dev1.sell.olivertest.com/login")) {
//			logger.pass("Pass");
//		}
//		else {
//			logger.fail("Fail");
//		}
	}
	
	@Test(priority = 2)
	public void loginTest() {
		logger = extentReport.createTest("loginTest");
		siteSelectionPage = loginPage.login(prop.getProperty("userName"), prop.getProperty("paasword"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus()==ITestResult.FAILURE) {
			logger.log(Status.FAIL, "Test case failed is "+ result.getName());
			logger.log(Status.FAIL, "Test case failed error is "+ result.getThrowable());
			String destination = TestUtill.takeScreenshotForExtentReport(result.getName());
//			logger.log(Status.FAIL, result.add)
		}
		else if (result.getStatus()==ITestResult.SUCCESS) {
			logger.log(Status.PASS, "Test case Success is "+ result.getName());
		}
		driver.close();
		extentReport.flush();
	}
	
	
}
