package com.capg.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.capg.base.TestBase;
import com.capg.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtils.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Capturing screesnshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.ScreenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtils.ScreenshotName+"><img src="+TestUtils.ScreenshotName+" height=200 width=200></img> </a>");
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+"FAIL");
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.ScreenshotName));
		report.endTest(test);
		report.flush();
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, "Skipped"+arg0.getName());
		report.endTest(test);
		report.flush();
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		test=report.startTest(arg0.getName());
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	test.log(LogStatus.PASS, arg0.getName().toUpperCase()+"PASS");
	report.endTest(test);
	report.flush();
		
	}

}
