package pageTest.testUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	
	public static Logger logger = LogManager.getLogger(Utility.class.getName());
	
	public static void info(String log4jMessage) {
		logger.info(log4jMessage); 
	}
	public static void info(int log4jMessage) {
		logger.info(log4jMessage); 
	}
}
