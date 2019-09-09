package cucumber;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.internal.junit.ArrayAsserts;

public class CucumFeatOver {

	public static void main(String ... args) throws IOException {
		CucumFeatOver of = new CucumFeatOver();
		of.overrideFeatFile();
	}

	public void overrideFeatFile() {
		String featPath = "src/test/java/cucumber/new2.feature";
		String featTxt = "src/test/java/cucumber/new2.txt";
		String ll = null;
		BufferedReader featRead = null;
		BufferedWriter featWrite = null;
		
		try {
			featRead = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(featPath)), "UTF-8"));
			featWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(featTxt, false), "UTF-8")); // true to append
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			String line = "";
			String scenName = "";

			while ((ll = featRead.readLine()) != null) {

				if(ll.contains("Scenario")) {
					String[] sName = null;
					sName = ll.split(":");
					scenName = sName[1].trim();
					line += ll.trim()+"\n";
				}
				else if (!ll.contains("##")) {//Store line value
					line += ll.trim()+"\n";
				}
				else if (ll.contains("##")) {//Extract excel path and sheet name and write Feature file with data
					String[] llArr = ll.split("@");
					String sheetName = llArr[1].trim();
					String xcelPath = llArr[0].trim();
					xcelPath = xcelPath.substring(xcelPath.lastIndexOf("#")+1, xcelPath.length());
					List<ArrayList<String>> excelData = readExcelFile(scenName, xcelPath, sheetName);//Scenario without Examples
					
					for(int i=0; i<excelData.size(); i++) {
						for(int j=0; j<excelData.get(i).size(); j++) {
							line += " | " + excelData.get(i).get(j);
						}
						line += " | \n";
					}
				}
			}
System.out.println(line);
			featRead.close();
			System.out.println(">>Feature File is closed after reading");

			featWrite.write(line);
			featWrite.close();
			System.out.println(">>Feature File is closed after writing");
			
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static List<ArrayList<String>> readExcelFile(String scen, String xcelfileName, String sheetName) {
		String xcelPath = "src/test/java/cucumber/"+xcelfileName;
		FileInputStream excelFile = null;
		XSSFWorkbook wb = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		String validCellValue = null;

		try {
			excelFile = new FileInputStream(new File(xcelPath));
			wb = new XSSFWorkbook(excelFile);
			sheet = wb.getSheet(sheetName);
			System.out.println(">>Excel File is opened");
		} catch (FileNotFoundException e) {
			System.out.println(">>Could not open the Excel File");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(">>Could not open the Excel Sheet");
			e.printStackTrace();
		}

		int rowCount = sheet.getLastRowNum();

		// Find matching scenario
		int c = 1; // skip first row
		for (int r = 1; r < rowCount; r++, c++) {
			cell = sheet.getRow(r).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			validCellValue = getCellData(cell);

			if (validCellValue.equals(scen)) {
				break;
			}
		}

		// Find next scenario
		int v = c;
		for (int s = c + 1; s < rowCount; s++, v++) {
			cell = sheet.getRow(s).getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			validCellValue = getCellData(cell);

			if (validCellValue.length() > 1 && !validCellValue.equals(scen)) {
				break;
			}
		}

		// Make list of rows from one scenario to another scenario
		List<ArrayList<String>> outerList = new ArrayList<ArrayList<String>>();
		ArrayList<String> innerList = null;
		for (int rows = c; rows <= v; rows++) {
			innerList = new ArrayList<String>();
			int cellCount = sheet.getRow(c).getLastCellNum();// Depends on row num

			for (int cells = 1; cells < cellCount; cells++) {
				cell = sheet.getRow(rows).getCell(cells, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				validCellValue = getCellData(cell);
				innerList.add(validCellValue);
			}
			outerList.add(innerList);
		}

		try {
			wb.close();
			excelFile.close();
			System.out.println(">>Excel File is closed");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outerList;
	}

	public static String getCellData(XSSFCell cell) {
		String cellData = null;

		try {
			switch (cell.getCellTypeEnum()) {
			case NUMERIC:
				cellData = String.valueOf(cell.getNumericCellValue()).trim();
				break;
			case STRING:
				cellData = cell.getStringCellValue().trim();
				break;
			case BOOLEAN:
				cellData = String.valueOf(cell.getBooleanCellValue()).trim();
			case BLANK:
				cellData = "";
//			case ERROR:
//				cellData = "";
			default:
				cellData = cell.getStringCellValue().trim();
				break;
			}
		} catch (Exception e) {
			System.out.println(">>No Cell DataType is available");
			System.out.println(">>Cell: " + e.getMessage());
		}
		return cellData;
	}

}
