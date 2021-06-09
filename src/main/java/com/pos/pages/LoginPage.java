package com.pos.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pos.base.TestBase;

public class LoginPage extends TestBase{

	@FindBy(xpath="//*[@id='username']")
	WebElement userName;
	
	@FindBy(xpath="//*[@id='password']")
	WebElement paasword;
	
	@FindBy(xpath="//button[contains(text(),'Sign In')]")
	WebElement signInButton;
	

	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public SiteSelectionPage login(String un, String pwd) {
		userName.sendKeys(un);
		paasword.sendKeys(pwd);
		signInButton.click();
		return new SiteSelectionPage();
	}
}
