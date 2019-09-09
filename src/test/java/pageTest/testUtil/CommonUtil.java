package pageTest.testUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonUtil {
	
	//Keep all the common functionalities in this class
	
	public static void inputKeysToEle(WebElement ele, String val) {
		ele.clear();
		ele.sendKeys(val);
	}
	
	public static void clickEle(WebElement ele) {
		ele.click();
	}
	public static void clickEleJsExec(WebDriver driver, WebElement ele) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);

	}
	
	public static void submitEle(WebElement ele) {
		ele.submit();
	}
	
	public static Select selectEleFromDropDown(WebElement ele) {
		Select sel = new Select(ele);
		return sel;
	}

	public static String getTextOfEle(WebElement ele) {
		String val = ele.getText().trim();
		return val;
	}
	
	public static String getAttributeOfEle(WebElement ele, String attrName) {
		String val = ele.getAttribute(attrName).trim();
		return val;
	}

	public static void waitDriverUntilElementIsVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Utility.WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void waitDriverUntilElementIsClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Utility.WAIT_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void waitDriverUntilPresenceOfElement(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Utility.WAIT_TIME);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	public static void waitDriverUntilNoOfElement(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Utility.WAIT_TIME);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
		
	}
		
	public static void scrollIntoView(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}
	public static void scrollToTheBottom(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	

}
