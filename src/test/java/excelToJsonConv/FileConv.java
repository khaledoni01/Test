package excelToJsonConv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;


public class FileConv {

	@Test(priority = 0)
	public void jsonReading() {
		String jsonPath = "C:/Users/User/Desktop/dest.json";
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader(jsonPath)){
			Object obj = jsonParser.parse(reader);
			
//			Map<String, String> wb = JsonPath.read(obj, "$");
//			System.out.println(wb.keySet());
			List<List<String>> cols = JsonPath.read(obj, "$.Info[*]"); //Column
			System.out.println(cols);//.get(0).get(0)
			List<String> rows = JsonPath.read(obj, "$.Info.FirstName"); //Row
			System.out.println(rows);//.get(0)			
						
		} catch(IOException | ParseException e) {
			e.getMessage();
		}
		
		
	}
	
//	@Test(priority = 0)
	public void excelToJson() {
		String xcelPath = "C:/Users/User/Desktop/move.xlsx";
		String jsonPath = "C:/Users/User/Desktop/dest.json";
		
		JSONObject sheetObj = new JSONObject();
		JSONArray sheetArray = new JSONArray();
		JSONObject colObj = new JSONObject();
		JSONArray rowArray = new JSONArray();
		
		FileInputStream excelFile = null;
		XSSFWorkbook wb = null;
	    int sheetCount = 0;
		
		try {
			excelFile = new FileInputStream(new File(xcelPath));
			wb = new XSSFWorkbook(excelFile);
			sheetCount = wb.getNumberOfSheets();
		    System.out.println("Excel File is opened");    
	    }
		catch (FileNotFoundException e) {
	    	System.out.println("Could not open the Excel File");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("Could not open the Excel Sheet");
			e.printStackTrace();
		}
		
		// Looping all sheets
		for(int sh=0; sh < sheetCount; sh++) {
			String validCellValue = null;		    
			
			XSSFSheet sheet = wb.getSheetAt(sh);
			String sheetName = wb.getSheetName(sh);
						
			XSSFRow row = null;
			XSSFCell cell = null;
			int sheetCellCount = 0;
			
			if(sh > 0) {
				sheetArray = new JSONArray();
				colObj = new JSONObject();
			}
sheetArray.add(sheetName);

			try {
				sheetCellCount = sheet.getRow(0).getLastCellNum();
			}catch(NullPointerException e) {
				System.out.println(sheet.getSheetName()+" sheet is: " + e.getMessage());
			}
					
			// Looping all cell
		    for(int cd=0; cd < sheetCellCount; cd++) {				
		   	    String firstRow = null;

		    	if(cd >= 0) {
			    	rowArray = new JSONArray();
		    	}
				Iterator<Row> rows = sheet.rowIterator();
				int c = 0;
						   	    
		   	    // Looping all rows
		   	    while(rows.hasNext()) {
			    	row = (XSSFRow) rows.next();
			    	cell = row.getCell(cd, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			    				    	
   					if(c == 0) {
   						firstRow = getCellData(cell);
   						c++;
   					}
   					else if(row.getRowNum() > 0 && cell != null && c > 0) {
		    			validCellValue = getCellData(cell);		
rowArray.add(validCellValue);
		    		}
		    		else if(cell == null) {break;}
		    	}
colObj.put(firstRow, rowArray);
		    }
sheetObj.put(sheetArray.get(0), colObj);
		}

		try (FileWriter jsonFile = new FileWriter(jsonPath)) {
			jsonFile.write(sheetObj.toJSONString());
			jsonFile.close();
			System.out.println("JSON File is closed");

			wb.close();
			excelFile.close();
			System.out.println("Excel File is closed");

			System.out.println(sheetObj);
		}catch (IOException e) {
			System.out.println("Couldn't write JSON file");
			e.printStackTrace();
		}
		
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
			case BLANK:
				cellData = "";
			case BOOLEAN:
				cellData = String.valueOf(cell.getBooleanCellValue()).trim();
			default:
				cellData = cell.getStringCellValue().trim();
				break;
			}
		} catch (Exception e) {
			System.out.println("No Cell DataType is available");
			e.printStackTrace();
		}
		return cellData;
	}


}
