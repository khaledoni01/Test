
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class tester {

	public static void main(String[] args) {
		
		try {
			FileUtils.cleanDirectory(new File("C:/Users/User/Desktop/Del/*.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
