package testing;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class Tester {

	public static void main(String[] args) {
		
		
		try {
			int a = 10;
			int b = 0;
			int c = a / b;			
			System.out.println("Num: " + c);
		}
		catch(ArithmeticException msg) {
//			System.out.println(msg.getMessage());
			System.out.println("()()()(()(");
//			msg.printStackTrace();
		}
		
		System.out.println("hello");
		
		
		
//		try {
//			FileUtils.cleanDirectory(new File("C:/Users/User/Desktop/Del/*.txt"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}

}
