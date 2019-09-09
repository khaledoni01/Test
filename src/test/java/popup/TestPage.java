package popup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageTest.TestBase;


public class TestPage {
	
	public static WebDriver driver;
	
	@FindBy(xpath = "//button[1]")
    private WebElement modal1;
	@FindBy(xpath = "//button[2]")
	private WebElement modal2;
	@FindBy(xpath = "//button[3]")
	private WebElement modal3;
	@FindBy(xpath = "//span[@class='close']")
	private WebElement closeBtn;
	
	public TestPage(WebDriver driver) throws Exception {
        this.driver = driver;
        MyPageFactory.initElements(driver, this);
    }
    
	public TestPage launch() {
        driver.get("file:///C:/Users/User/Desktop/index.html");
        System.out.println("Launching Driver");
        return this;
	}
	
    public TestPage modal1() {
    	this.modal1.click();
    	System.out.println("Modal1");
//    	this.closePopup();
    	return this;
    }
    public TestPage modal2() {
    	this.modal2.click();
    	System.out.println("Modal2");
//    	this.closePopup();
    	return this;
    }
    public TestPage modal3() {
    	this.modal3.click();
    	System.out.println("Modal3");
//    	this.closePopup();
    	return this;
    }
//    public TestPage close() {
//    	this.closeBtn.click();
//    	System.out.println("close popup");
//    	return this;
//    }
    
    public void closePopup() {
    	this.closeBtn.click();
    	System.out.println("close popup");
    }
    public void verify() {
    	Assert.assertTrue(!this.closeBtn.isDisplayed());
    }
    
    public static void main(String...args) throws Exception {
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/browser/chromedriver_76.exe");
    	WebDriver driver = new ChromeDriver();  
    	TestPage tp = new TestPage(driver);
    	
	    tp.launch()
	  	  .modal1()
	  	  .modal2()
	  	  .modal3()
	  	  .verify();
//	  	  .closePopup();
    }
    
//    @Test(priority = 0)
    public void testOne() throws Exception {
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/browser/chromedriver_76.exe");
    	WebDriver driver = new ChromeDriver();  
    	TestPage tp = new TestPage(driver);
    	
	    tp.launch()
	  	  .modal1()
	  	  .modal2()
	  	  .modal3()
	  	  .verify();
//	  	  .closePopup();


    }
    
}