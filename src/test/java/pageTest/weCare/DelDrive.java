package pageTest.weCare;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class DelDrive {
	
	
	public static void main(String...args) {
		try {
			FileUtils.cleanDirectory(new File("C:\\Users\\User\\Desktop\\Del"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
