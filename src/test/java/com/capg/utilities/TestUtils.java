package com.capg.utilities;

import com.capg.base.TestBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

public class TestUtils extends TestBase{
	
	public static String ScreenshotName;
	
	public static void captureScreenshot() throws IOException
	{
	
		File srcfile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date dt=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("YYYYMMdd-HHmmss");
		//String ScreenshotName = "Image"+sd.format(new Date())+".jpg";
		ScreenshotName=dt.toString().replace(":", "").replace(" ", "")+".jpg";
		//ScreenshotName="error.jpg";
		FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+ScreenshotName));
		
		
		
		
	}
	
	public static boolean isTestRunnable(String testName,ExcelReader excel)
	{
		String sheetname="test_suite";
		
		int rows = excel.getRowCount(sheetname);
		
		for(int rownum=2;rownum<=rows;rownum++)
		{
		String testcase=excel.getCellData(sheetname, "TC Name", rownum);
		if(testcase.equalsIgnoreCase(testName))
		{
			String runmode=excel.getCellData(sheetname, "RunMode", rownum);
			if(runmode.equalsIgnoreCase("Y"))
				return true;
			else
				return false;
		}
		}
		return false;
		
	}
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m)
	{
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}
		System.out.println(table);
System.out.println(data);
		return data;

	}


}
