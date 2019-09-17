package pageTest;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	int counter = 0;
	int retryLimit = 1;
	
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
