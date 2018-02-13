package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BasePage;
import com.qa.utils.Testutils;

public class LoginPage extends BasePage {
	
	//Pagefactory - OR
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement LoginButton;
	
	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
	WebElement Signupbtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement CRMImg;
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String LoginPageTitle()
	{
		String title=driver.getTitle();
		System.out.println(title);
		return title;
	}
	
	public boolean validateCrmimg()
	{
		return CRMImg.isDisplayed();	
	}
	
	//after login landing page is Home page hence we need to make the method of homepage
	
	public HomePage Login(String un,String pwd)
	{
		username.sendKeys(un);
		//waitForSeconds(Testutils.Implicit_Wait);
		password.sendKeys(pwd);
		waitForSeconds(Testutils.Implicit_Wait);
		LoginButton.click();
		waitForSeconds(Testutils.Implicit_Wait);
		
		return new HomePage();
	}
}
