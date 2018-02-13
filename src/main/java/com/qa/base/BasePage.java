package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.utils.Testutils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage {

	public static WebDriver driver;
	public static Properties prop;

	public static ExtentReports extent;
	public static ExtentTest test;

	public BasePage() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"C:\\Users\\Shashank\\workspace\\POMHybridFramework\\src\\main\\java\\com\\qa\\config\\config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "S:/Softwares/eclipse/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browsername.equals("ff")) {
			System.setProperty("webdriver.gecko.driver", "S:/Softwares/eclipse/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Testutils.Implicit_Wait, TimeUnit.SECONDS);

		/*
		 * driver.manage().deleteAllCookies();
		 * driver.manage().timeouts().pageLoadTimeout(Testutils.
		 * Page_Load_Timeout, TimeUnit.SECONDS);
		 * driver.manage().timeouts().implicitlyWait(Testutils.Implicit_Wait,
		 * TimeUnit.SECONDS);
		 */

	}

	protected void waitForSeconds(long implicit_Wait) {
		try {
			Thread.sleep(implicit_Wait * 1000);
		} catch (InterruptedException e) {
		}
	}

	// adding extent report method

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		extent = new ExtentReports(System.getProperty("user.dir") + "/src/test/java/Report/test"
				+ formater.format(calendar.getTime()) + ".html", false);
	}

	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP,
					result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}
	
	@AfterMethod()
	public void afterMethod(ITestResult result) {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		extent.endTest(test);
		extent.flush();
	}

}