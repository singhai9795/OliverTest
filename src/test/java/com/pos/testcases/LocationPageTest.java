package com.pos.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pos.base.TestBase;
import com.pos.pages.LocationPage;
import com.pos.pages.LoginPage;
import com.pos.pages.SiteSelectionPage;

public class LocationPageTest extends TestBase{
	LoginPage loginPage;
	SiteSelectionPage siteSelectionPage;
	LocationPage locationPage;
	
	LocationPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		siteSelectionPage = loginPage.login(prop.getProperty("userName"), prop.getProperty("paasword"));
		locationPage = siteSelectionPage.clickOnSiteLink();
	}
	
	@Test
	public void clickGoBackButtonTest() {
		locationPage.clickOnGoBackBtn();
		String currentUrl = loginPage.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://dev1.sell.olivertest.com/site_link");
	}
	
	@Test
	public void selectLocationTest() throws InterruptedException {
		List<WebElement> locationList = driver.findElements(By.xpath("//*[contains(@id,'loginLocationTab')]"));
		
		for(WebElement e : locationList) {
			System.out.println(e.getText());
			if(e.getText().equals("Varun Shop 1")) {
				locationPage.selectLocation("Varun Shop 1");
				Thread.sleep(3000);
//				System.out.println("Location selected 1st- moved to register page");
				boolean locationSelected = locationPage.locationSelected("Varun Shop 1");
				Assert.assertTrue(locationSelected);
				return;
			}
			else if(e.getText().equals("Location Two2")) {
				locationPage.selectLocation("Location Two");
//				System.out.println("Location selected 2nd- moved to register page");
				boolean locationSelected = locationPage.locationSelected("Location Two2");
				Assert.assertTrue(locationSelected);
				return;
			}
			else if(e.getText().equals("Test location2")){
				locationPage.selectLocation("Test location");
//				System.out.println("Location selected 3rd- moved to register page");
				boolean locationSelected = locationPage.locationSelected("Test location2");
				Assert.assertTrue(locationSelected);
				return;
			}
			else {
//				WebElement loca3 = driver.findElement(By.xpath("//*[contains(@id,'loginLocationTab3')]"));
//				WebElement loca4 = driver.findElement(By.xpath("//*[contains(@id,'loginLocationTab4')]"));
				WebElement loca5 = driver.findElement(By.xpath("//*[contains(@id,'loginLocationTab5')]"));
//				JavaScriptExecutorConcept.scrollIntoView(loca);
//				System.out.println(loca);
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true);",loca5);
				if(e.getText().equals("Location Name Fivee")) {
					locationPage.selectLocation("Location Name Five");
//					System.out.println("Location selected- Location Name Five");
					boolean locationSelected = locationPage.locationSelected("Location Name Five");
					Assert.assertTrue(locationSelected);
					return;
				}
				else if(e.getText().equals("location check")) {
					locationPage.selectLocation("location check");
//					System.out.println("Location selected- location check");
					boolean locationSelected = locationPage.locationSelected("location check");
					Assert.assertTrue(locationSelected);
					return;
				}
				else if(e.getText().equals("location 101")) {
					locationPage.selectLocation("location 101");
//					System.out.println("Location selected- location 101");
					boolean locationSelected = locationPage.locationSelected("location 101");
					Assert.assertTrue(locationSelected);
					return;
				}
			}
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
