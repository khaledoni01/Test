package testing;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;


public class Tester {

	public static void main(String[] args) {
		
		int[] dupArr = {10, 20, 5, 10, 39, 40, 10, 20, 33, 33, 45, 40};
		Set<Integer> st = new HashSet<Integer>();
		
		for(int i=0; i<dupArr.length; i++) {
			
			for(int j=i+1; j<dupArr.length; j++) {
				
				if(dupArr[i]==dupArr[j]) {
					st.add(dupArr[j]);
				}
			}			
		}
		for(int s : st) {
			System.out.println("Duplicate: " + s);
		}
		
		
		
//		int[] maxArr = {10, 20, 5, 10, 39, 40, 10};
//		int max = maxArr[0];
//		
//		for(int i=0; i<maxArr.length; i++) {
//			
//			if(maxArr[i]> max) {
//				max = maxArr[i];
//			}
//		}
//		System.out.println(max);
				
		
//		try {
//			FileUtils.cleanDirectory(new File("C:/Users/User/Desktop/Del/*.txt"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}

}
