package com.pos.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pos.base.TestBase;
import com.pos.pages.LocationPage;
import com.pos.pages.LoginPage;
import com.pos.pages.SiteSelectionPage;

public class SiteSelectionTest extends TestBase{
	LoginPage loginPage;
	SiteSelectionPage siteSelectionPage;
	
	SiteSelectionTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		siteSelectionPage = loginPage.login(prop.getProperty("userName"), prop.getProperty("paasword"));
	}
	
	@Test(priority = 1)
	public void clickOnSiteLink() throws InterruptedException {
		LocationPage locationPage = siteSelectionPage.clickOnSiteLink();
		String currentURL = siteSelectionPage.getCureentUrl();
		Assert.assertEquals(currentURL, "https://dev1.sell.olivertest.com/login_location","Navigation to Location page failed");
		Thread.sleep(3000);
	}
	
//	@Test(priority = 1)
//	public void getCureentUrlTest() {
//		String currentURL = siteSelectionPage.getCureentUrl();
//		Assert.assertEquals(currentURL, "https://dev1.sell.olivertest.com/site_link");
//	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
