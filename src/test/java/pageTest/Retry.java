package pageTest;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class Retry implements IRetryAnalyzer{
	
	int counter = 0;
	int retryLimit = 0;
	
	public boolean retry(ITestResult result) {

		if (!result.isSuccess()) {
			if(counter < retryLimit) {
				counter++;
				return true;
			}		
			
		}else {
            result.setStatus(ITestResult.SUCCESS);
        }
		return false;
	}
	
	
}
