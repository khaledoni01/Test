package pageTest.testUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Utility {
	
	
	public static final int WAIT_TIME = 20;
	public static Properties properties = null;
	public static final String PropFilePath = System.getProperty("user.dir")+"/configuration/config.properties";
	public static final String ExcelFilePath = System.getProperty("user.dir") + Utility.propertiesFile(Utility.PropFilePath).getProperty("excelPath");
		
	
	public static Properties propertiesFile(String propFilePath) {
		properties = new Properties();
		FileInputStream file;
			
		try {
			file = new FileInputStream(PropFilePath);
			properties.load(file);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		return properties;
	}
	

//	public static String setDate() {
//		SimpleDateFormat dFormat = new SimpleDateFormat("MM/dd/yyyy");
//		Calendar c = Calendar.getInstance();
//		c.setTime(new Date());
//		c.add(Calendar.DATE, 1);
//		String date = dFormat.format(c.getTime());
//		return date;
//	}
	
	

}
