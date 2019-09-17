package pageTest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.LogStatus;
import pageTest.testUtil.Log;

public class TestListener extends TestBase implements ITestListener {

	
	public void onTestStart(ITestResult result) {
		Log.logger.info("Test started: " + result.getName());
		test = extent.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		Log.info("Test Succeded: " + result.getName());
		test.log(LogStatus.PASS, "Test Case Passed is "+result.getName());
		extent.endTest(test);
	}

	public void onTestFailure(ITestResult result) {
		Log.info("Test Failed: " + result.getName());
		
//		String filePath = System.getProperty("user.dir")+"/screenshot/failed_"+ result.getMethod().getMethodName()+".png";
//		TakesScreenshot scrShot = (TakesScreenshot) driver;
//		File scrFile= scrShot.getScreenshotAs(OutputType.FILE);
//		File desFile = new File(filePath);
//		try {
//			FileUtils.copyFile(scrFile, desFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) TestBase.getDriver()).getScreenshotAs(OutputType.BASE64);
//        test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName() + test.addBase64ScreenShot(base64Screenshot));
		test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		extent.endTest(test);
	}

	public void onTestSkipped(ITestResult result) {
		Log.info("Test Skipped: " + result.getName());
		test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		extent.endTest(test);
	}

	public void onStart(ITestContext context) {
		Log.info("On Start: " + context.getName());
	}

	public void onFinish(ITestContext context) {
		Log.info("On End: " + context.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}



}
