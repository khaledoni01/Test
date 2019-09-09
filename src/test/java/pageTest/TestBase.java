package pageTest;


import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Test;

import pageTest.testUtil.Log;
import pageTest.testUtil.Utility;
import pageTest.weCare.ClassOne;
import pageTest.weCare.ClassTwo;


public class TestBase {

	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static ClassOne classOne;	
	public static ClassTwo classTwo;
	
	public static ClassOne classOneObject() {
	return classOne = new ClassOne();		
}
	public static ClassTwo classTwoObject() {
	return classTwo = new ClassTwo();		
}

	
	@BeforeSuite(groups = "testOne")
	@Parameters("browser")
	public void setupDriver(String browser) {		
		System.out.println("Before Suite");
//		driver = TestBase.openBrowser(browser);
//		String baseURL = Utility.propertiesFile(Utility.PropFilePath).getProperty("baseURL");
//		Log.info("Driver is launched");
//		
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(baseURL);
	}
	
//	@AfterSuite(alwaysRun = true)
	public void tearDownDriver() {
		System.out.println("After Suite");
		if (driver != null) {
			driver.quit();
		}
		driver = null;
		Log.info("Driver is closed");
	}
	
	@BeforeTest(alwaysRun = true) // groups="testOne"
	public void setupExtentReport() {
		System.out.println("Before Test");
		TestBase.setupReport();
	}
	@AfterTest(alwaysRun = true)
	public void tearDownExtentReport() {
		System.out.println("After Test");
		TestBase.tearDownReport();
	}


	
	public static void setupReport() {
		extent = new ExtentReports(System.getProperty("user.dir")+"/extentReport/ExtentReport.html", true);	
		extent.addSystemInfo("Host Name","Test Project")
		.addSystemInfo("Environment","QA Automation Testing")
		.addSystemInfo("User Name","Khaled Zaman");
		extent.loadConfig(new File(System.getProperty("user.dir")+"/src/test/resources/extent-config.xml"));
	}
	public static void tearDownReport() {
		extent.flush();
		extent.close();		
	}

	public static WebDriver openBrowser(String browserName) {
		
		if (browserName.contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/browser/chromedriver_76.exe");
			driver = new ChromeDriver();
//			try {
//			driver = new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"), DesiredCapabilities.chrome());
//			} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
			Log.info("Chrome Driver is initiated");
			
		}else if (browserName.contains("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/browser/geckodriver.exe");
			driver = new FirefoxDriver();
			Log.info("Firefox Driver is initiated");
			
		}else if (browserName.contains("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/browser/IEDriverServer_32.exe");
			InternetExplorerOptions caps = new InternetExplorerOptions();
			caps.ignoreZoomSettings();
			caps.disableNativeEvents();
			driver = new InternetExplorerDriver(caps);
			Log.info("IE Driver is initiated");
		}
		return driver;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}			
		

}
