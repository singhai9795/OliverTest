package com.pos.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pos.base.TestBase;
import com.pos.pages.LoginPage;
import com.pos.pages.SiteSelectionPage;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	SiteSelectionPage siteSelectionPage;
	
	LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String pageTitle = loginPage.getPageTitle();
		Assert.assertEquals(pageTitle, "Oliver POS");
	}
	
	@Test
	public void blankUsernameTest() {
		loginPage.login("", prop.getProperty("paasword"));
		String currentUrl = loginPage.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://dev1.sell.olivertest.com/login","Moved to site selection page");
	}
	
	@Test(priority = 2)
	public void loginTest() {
		siteSelectionPage = loginPage.login(prop.getProperty("userName"), prop.getProperty("paasword"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	
}
