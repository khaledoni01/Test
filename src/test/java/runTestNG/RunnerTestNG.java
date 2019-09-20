package runTestNG;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Runner;
import org.testng.TestNG;

public class RunnerTestNG {

	
	public static void main(String...args) throws IOException {

		InputStream is = Runner.class.getResourceAsStream("/testng.xml");
		InputStreamReader isr = new InputStreamReader(is);
		File mysuite = new File("testng.xml");
		OutputStream os = new FileOutputStream(mysuite); // Create a instance of testng.xml file outside the .jar file

		int l = isr.read();

		while (l != -1) {
			byte b = (byte) l;
			os.write(b);
			l =isr.read();
		}
		
		os.close();
		isr.close();
		

//		FileInputStream fl = new FileInputStream("./config/prop.properties");
//		InputStream is = World.class.getResourceAsStream("/config/prop.properties");
		//FileInputStream fl = new FileInputStream(is);
//		Properties pr = new Properties();
//		pr.load(is);
//		System.out.println(pr.getProperty("a"));
		

		TestNG testng = new TestNG();

		List<String> testFilesList = new ArrayList<String>();
		testFilesList.add("testng.xml");
		// testFilesList.add("C:\\Users\\User\\eclipse-workspace\\RunTestNG\\testng.xml");
		testng.setTestSuites(testFilesList);
		testng.setUseDefaultListeners(false);
		testng.run();
	}
}
