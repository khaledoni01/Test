package pageTest.weCare;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;


public class PageValidation {

	
	public static void main(String[] args) {
		List<String> red = new ArrayList<String>();
		red.add("a");
		red.add("b");
		red.add("c");
		red.add("d");
		red.add("e");
		red.add("f");
		red.add("g");

		List<String> orange = new ArrayList<String>();
		orange.add("a");
		orange.add("b");
		orange.add("c");
		orange.add("d");
		orange.add("e");
		orange.add("f");

		List<String> reg = new ArrayList<String>();
		reg.add("a");
		reg.add("b");
		reg.add("c");
		reg.add("d");
		reg.add("e");
		reg.add("f");

		List<String> all = new ArrayList<String>();
		all.add("Pending");
		all.add("Assigned");
		all.add("Assigned");
		all.add("Assigned");
		all.add("InProgress");
		all.add("InProgress");
		all.add("InProgress");
		
		all.add("Pending");
		all.add("Assigned");
		all.add("Assigned");
		all.add("InProgress");
		all.add("InProgress");
		all.add("InProgress");		
	
		all.add("Pending");
		all.add("Pending");
		all.add("Pending");
		all.add("Assigned");
		all.add("InProgress");
		all.add("InProgress");
		
		String currVal = null;

		int totalErr = red.size() + orange.size();

		List<String> list = new ArrayList<String>();
		
		if(red.size() > 0){
			
			for(int r = 0; r < red.size(); r++) {
				String val = all.get(r);
				System.out.println("red - " + val);

				if(r == 0) {
					currVal = all.get(r);
					list.add(currVal);
				}
				if(!currVal.equalsIgnoreCase(val)) {
					Assert.assertFalse(list.contains(val),"list contains the current val: " + all.get(r));
					currVal = all.get(r);
					list.add(currVal);					
				}
			}
			list.clear();
		}
		if(orange.size() > 0) {
			currVal = null;

			for(int o = red.size(); o < totalErr; o++) {
				String val = all.get(o);
				System.out.println("orange - " + val);
				
				if(o == red.size()) {
					currVal = all.get(o);
					list.add(currVal);
				}
				if(!currVal.equalsIgnoreCase(val)) {
					Assert.assertFalse(list.contains(val),"list contains the current val: " + all.get(o));
					currVal = all.get(o);
					list.add(currVal);					
				}
			}
			list.clear();
		}
		if(reg.size() > 0) {
			currVal = null;

			for(int re = totalErr; re < all.size(); re++) {
				String val = all.get(re);
				System.out.println("reg - " + val);
				
				if(re == totalErr) {
					currVal = all.get(re);
					list.add(currVal);
				}
				if(!currVal.equalsIgnoreCase(val)) {
					Assert.assertFalse(list.contains(val),"list contains the current val: " + all.get(re));
					currVal = all.get(re);
					list.add(currVal);					
				}
			}
			list.clear();
		}
		
		

	}
}
