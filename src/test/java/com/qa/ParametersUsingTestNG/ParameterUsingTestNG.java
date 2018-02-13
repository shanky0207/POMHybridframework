package com.qa.ParametersUsingTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterUsingTestNG {
	
	WebDriver driver;
	
	@Test
	@Parameters({"url","emailId"})
	public void LoginYahoo(String url,String emailId)
	{
		System.setProperty("webdriver.chrome.driver", "S:/Softwares/eclipse/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		
		driver.findElement(By.xpath("//*[@id='login-username']")).clear();
		driver.findElement(By.xpath("//*[@id='login-username']")).sendKeys(emailId);
		driver.findElement(By.xpath("//*[@id='login-signin']")).click();
		
		driver.quit();
	}
	

}
