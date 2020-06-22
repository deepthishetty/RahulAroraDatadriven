package com.capg.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.capg.utilities.ExcelReader;
import com.capg.utilities.ExtentManager;
import com.capg.utilities.TestUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties config =new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	public static Logger logger=Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel =new ExcelReader("C:\\Users\\Deepthi\\eclipse-workspace\\com.datadriven.rahularora\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report=ExtentManager.getInstance();
	public static ExtentTest test;
	@BeforeSuite
	public void setup() throws IOException
	{
		if(driver==null)
		{
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			config.load(fis);
			logger.debug("Config file launched");
			
			fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			
			logger.debug("OR file launched");
		}
		
		if(config.getProperty("browser").equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(config.getProperty("browser").equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver=new ChromeDriver();
			logger.debug("Browser launched"+config.getProperty("browser"));
		}
		
		else if(config.getProperty("browser").equals("ie"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.get(config.getProperty("testsiteurl"));
		logger.debug("Website launched"+config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,5);
	}
	
	public boolean isElementPresent(By by)
		{
		try {
		driver.findElement(by);
		return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
	
	public void click(String locator)
	{
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		test.log(LogStatus.INFO, "clicking on "+locator);
	}
	
	
	public void type(String locator,String value)
	{
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		test.log(LogStatus.INFO, "typing on "+locator+"entered value "+value);
	}
	WebElement dropdown;
	public void select(String locator,String value)
	{
		dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
		
		Select select=new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "selecting from "+locator+"value is "+value);
	}
	
	public void verifyEquals(String Expected,String Actual) throws IOException
	{
		try {
			Assert.assertEquals(Expected, Actual);
		}
		catch(Throwable e)
		{
		TestUtils.captureScreenshot();	
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.ScreenshotName+"><img src="+TestUtils.ScreenshotName+" height=200 width=200></img> </a>");
		test.log(LogStatus.FAIL, "FAIL"+e.getMessage());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.ScreenshotName));
		}
	}
	
	@AfterSuite
	public void teardown()
	{
		driver.quit();
		logger.info("test execution completed");
	}

}
