package com.capg.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.capg.base.TestBase;
import com.capg.utilities.TestUtils;

public class OpenAccountTest extends TestBase {
	
	@Test(dataProviderClass=TestUtils.class,dataProvider="dp")
	public void openAccountTest(Hashtable<String,String> data)
	{
		if(!(TestUtils.isTestRunnable("openAccountTest", excel)))
		{
			throw new SkipException("Skkiping the test case as Run mode is NO");
		}
		click("openacct");
		select("customer", data.get("customer"));
		select("currency", data.get("currency"));
		click("process");
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

}
