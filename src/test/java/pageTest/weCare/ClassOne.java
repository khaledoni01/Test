package pageTest.weCare;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageTest.TestBase;
import pageTest.testUtil.ExcelUtil;
import pageTest.testUtil.Utility;


public class ClassOne extends TestBase {
	
	@Test(priority = 0, groups = {"regression", "testOne"})
	public void hello1() {
		System.out.println("Test One hello1()");
	}
	@Test(priority = 1, groups = {"regression", "testOne"})
	public void hello2() {
		System.out.println("Test One hello2()");
		Assert.assertTrue(false);
	}	
	@Test(priority = 2, groups = {"regression", "testOne"})
	public void hello3() {
		System.out.println("Test One hello3()");
	}
	
//	@Test(priority = 1, dataProvider = "data", groups = {"regression", "testOne"})
//	public void jello(List<ArrayList<String>> validSheet, ArrayList<String> inputSheet) {
//		System.out.println("Test Two Input Sheet: " + inputSheet.get(0));
//		System.out.println("Test Two Valid Sheet: " + validSheet.get(0).get(0));
//	}
//	@DataProvider(name = "data")
//	public static Object[][] checkInData() {
//		String inputSheet = "LogIn_Input";
//		String validSheet = "LogIn_Valid";
//		return ExcelUtil.getTableArray(Utility.ExcelFilePath, validSheet, inputSheet);
//	}
//	@DataProvider(name = "data")
//	public static Object[][] checkInData() {
//		String inputSheet = "LogIn_Input";
//		return ExcelUtil.getTableArray(Utility.ExcelFilePath, inputSheet);
//	}
	
//	@Test(priority = 0, groups = {"regression"})
//	public void setDate() {  
//		SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd/yyyy");
//		Calendar c = Calendar.getInstance();
//		c.setTime(new Date());
//		c.add(Calendar.DATE, 1);
//		String date = dFormat.format(c.getTime());
//		String date = dFormat.format(new Date());
//		System.out.println(date);
//	}

	
	
}
