package pageTest.weCare;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Testing {

	@Test(priority = 0)
    public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(new Date());
		System.out.println(date);
	}
	
}
