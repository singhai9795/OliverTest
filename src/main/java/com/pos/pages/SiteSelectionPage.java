package com.pos.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pos.base.TestBase;

public class SiteSelectionPage extends TestBase{

	@FindBy(xpath="//li[@id='siteLinkTab0']")
	public WebElement siteLink;
	
	SiteSelectionPage(){
		PageFactory.initElements(driver, this);
	}
	
	public LocationPage clickOnSiteLink() {
		siteLink.click();
		return new LocationPage();
	}
	
	public String getCureentUrl() {
		return driver.getCurrentUrl();
	}
}
