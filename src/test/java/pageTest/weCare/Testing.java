package pageTest.weCare;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Testing {

	@Test(priority = 0)
    public void test() {
	    Calendar cal = Calendar.getInstance();
	    System.out.println("Today : " + cal.getTime());
	    cal.add(Calendar.DATE, -30);
	    System.out.println(cal.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String modDate = sdf.format(cal.getTime());
		System.out.println(modDate);
	}
	
}
