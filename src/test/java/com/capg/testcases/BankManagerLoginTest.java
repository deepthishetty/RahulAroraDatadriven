package com.capg.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.capg.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	@Test
	public void loginasBankManager() throws InterruptedException, IOException
	{
		//System.setProperty("org.uncommons.reportng.escape-output", "false");
		//verifyEquals("xyz", "abc");
		Thread.sleep(3000);
		click("bmlbutton");
		
		//driver.findElement(By.cssSelector(OR.getProperty("bmlbutton"))).click();
		logger.debug("clicked on bank manager login button");
		Thread.sleep(2000);
		System.out.println("login succes");
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addcustomerbtn"))),"login not successfull");
		
		if(isElementPresent(By.cssSelector(OR.getProperty("addcustomerbtn"))))
				{
			System.out.println("Login done");
				}
		else
		{
			System.out.println("Login not done");
		}
		logger.debug("Login successfull");
		//Assert.fail("BM not succesfully");
		
		//Reporter.log("Login Successful");
		//Reporter.log("<br>");
		//Reporter.log("<a target=\"_blank\" href=\"C:\\Images\\login.jpg\"><img src=\"C:\\Images\\login.jpg\" height=200 width=200></img> </a>");
	}

}
