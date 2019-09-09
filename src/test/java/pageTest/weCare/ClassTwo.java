package pageTest.weCare;


import org.testng.annotations.Test;

import pageTest.TestBase;


public class ClassTwo extends TestBase {

	@Test(priority = 0, groups = "testTwo")
	public void apple() {		
		System.out.println("Test Two");
	}
	@Test(priority = 1, groups = "testTwo")
	public void tomato() {		
		System.out.println("Test Two");
	}

}
