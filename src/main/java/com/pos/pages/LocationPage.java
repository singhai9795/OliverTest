package com.pos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pos.base.TestBase;

public class LocationPage extends TestBase{
	
	@FindBy(xpath="//*[contains(text(),' Go Back')]")
	WebElement goBackBtn;
	
	LocationPage(){
		PageFactory.initElements(driver,this);
	}
	
	public void selectLocation(String locationName) {
		driver.findElement(By.xpath("//*[contains(text(),'"+locationName+"')]")).click();
	}
	
	public boolean locationSelected(String locationSelected) {
		return driver.findElement(By.xpath("//div[contains(text(),'"+locationSelected+"')]")).isDisplayed();
	}
	
	public SiteSelectionPage clickOnGoBackBtn() {
		goBackBtn.click();
		return new SiteSelectionPage();
	}
}