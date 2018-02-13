package com.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginPageTest extends BasePage {
	
	LoginPage loginpage;
	HomePage homepage;
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		log.info("****************************** Starting test cases execution  *****************************************");
		initialization();
		loginpage=new LoginPage();
		
	}
	
	@Test(priority=3)
	public void Logintitle()
	{
		
		String verifytitle=loginpage.LoginPageTitle();
		Assert.assertEquals("#1 Free CRM for Any Business: Online Customer Relationship",verifytitle);
	}
	
	@Test(priority=2)
	public void ValidateCRMLogo()
	{
		boolean flag=loginpage.validateCrmimg();
		Assert.assertTrue(flag);
	}
	
	
	@Test(priority=1)
	public void Logintest()
	{
		log.info("****************************** starting test case *****************************************");
		log.info("****************************** freeCrmTitleTest *****************************************");
		 homepage=loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
		log.info("****************************** ending test case *****************************************");
		log.info("****************************** freeCrmTitleTest *****************************************");
	}
	@AfterMethod
	public void teradown()
	{
		driver.quit();
	}

}
