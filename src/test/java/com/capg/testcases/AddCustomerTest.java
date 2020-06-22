package com.capg.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.capg.base.TestBase;
import com.capg.utilities.TestUtils;

public class AddCustomerTest extends TestBase{
	
	@Test(dataProviderClass=TestUtils.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException
	
	{
		if(!(data.get("runmode").equals("Y")))
		{
			throw new SkipException("Not running");
		}
		System.setProperty("org.uncommons.reportng.escape-output", "false");
	click("addcustomerbtn");
		Thread.sleep(3000);
		System.out.println(data);
		System.out.println(data.get("firstname"));
		System.out.println(data.get("lastname"));
		System.out.println(data.get("postcode"));
		type("firstname",data.get("firstname"));
		type("lastname",data.get("lastname"));
		type("postcode",data.get("postcode"));
		click("addbtn");
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		//testing github
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		Thread.sleep(3000);
		alert.accept();
		Thread.sleep(3000);
		Reporter.log("Customer added succesfully");
		//Assert.fail("Add customer not succesfullu");
		
		
	}
	
	/*@DataProvider
	public Object[][] getData()
	{
		String sheetname="AddCustomerTest";
		int rows=excel.getRowCount(sheetname);
		int column=excel.getColumnCount(sheetname);
		Object[][] data=new Object[rows-1][column];
		
		for(int rownum=2;rownum<=rows;rownum++)
		{
			for(int colcount=0;colcount<column;colcount++)
			{
				data[rownum-2][colcount]=excel.getCellData(sheetname, colcount, rownum);
			}
		}
		
		return data;
		
	}*/

}
