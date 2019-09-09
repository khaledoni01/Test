package com.weCare.Library;

import java.util.Date;

public class Tester {
	
	
	public static void main(String...args) {
		Date date = new Date();
		System.out.println(date.getTime());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Date dateOne = new Date();
		System.out.println(dateOne.getTime());
	}

}
