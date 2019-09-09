package pageTest.testUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.core.appender.rolling.RolloverFrequency;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	
	
//	static FileInputStream excelFile = null;
//	static XSSFWorkbook wb = null;
//	static XSSFSheet sheet = null;
//	static XSSFRow row = null;
//	static XSSFCell cell = null;
//	static ArrayList<String> innerArrayList = null;
//	static List<ArrayList<String>> outerArrayList = null;
//	static int sheetCellCount = 0;
//	static boolean bool = false;
	
	
	public static Object[][] getTableArray(String excelFilePath, String inpSheet) {
		
		List<ArrayList<String>> inputSheetData = ExcelUtil.readExcelData(excelFilePath, inpSheet);		
		
		int inpRowSize = inputSheetData.size();
		Object[][] tabArray = new Object[inpRowSize][1];
		
		int col=0;
		for(int row=0; row<inpRowSize; row++) {
			tabArray[row][col] = inputSheetData.get(row);
		}
		return tabArray;
	}

	
	public static Object[][] getTableArray(String excelFilePath, String valSheet, String inpSheet) {
		
		List<ArrayList<String>> validSheetData = ExcelUtil.readExcelData(excelFilePath, valSheet);
		List<ArrayList<String>> inputSheetData = ExcelUtil.readExcelData(excelFilePath, inpSheet);		
		
		int inpRowSize = inputSheetData.size();
		Object[][] tabArray = new Object[inpRowSize][2];
		
		int col=0;
		for(int row=0; row<inpRowSize; row++) {
			tabArray[row][col] = validSheetData;
			tabArray[row][col+1] = inputSheetData.get(row);
		}
		return tabArray;
	}

	
	public static List<ArrayList<String>> readExcelData(String excelFilePath, String excelSheet) {

		FileInputStream excelFile = null;
		XSSFWorkbook wb = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
	    XSSFCell cell = null;
	    ArrayList<String> innerArrayList = null;
	    List<ArrayList<String>> outerArrayList = null;
	    int sheetCellCount = 0;
		boolean bool = false;
		
		try {
			excelFile = new FileInputStream(new File(excelFilePath));
			wb = new XSSFWorkbook(excelFile);
			sheet = wb.getSheet(excelSheet);
		    Log.info("Excel File is opened");

	    }catch (FileNotFoundException e) {
			Log.info("Could not read the Excel File");
			e.printStackTrace();
		}catch (IOException e) {
			Log.info("Could not read the Excel Sheet");
			e.printStackTrace();
		}
		
		if(excelSheet.contains("_Valid")) {
			
			outerArrayList = new ArrayList<ArrayList<String>>();
			try {
				sheetCellCount = sheet.getRow(0).getLastCellNum();	
//System.out.println("Valid Cell Count: " + sheetCellCount);
			}catch(NullPointerException e) {
				Log.info(sheet.getSheetName()+" sheet is: " + e.getMessage());
			}
			String validCellValue = null;
		    
		    for(int cd=0; cd<sheetCellCount; cd++) {
		    	innerArrayList = new ArrayList<String>();
		   	    Iterator<Row> rows = sheet.rowIterator();

		   	    while(rows.hasNext()) {
			    	row = (XSSFRow) rows.next();
			    	cell = row.getCell(cd);
		    		
		    		if(row.getRowNum() > 0 && cell != null) {
		    			validCellValue = getCellData(cell);
		    			innerArrayList.add(validCellValue); 
		    			bool = true;
		    		}else if(cell == null) {break;}
		    	}
		   	    if(bool == true) {
			    	outerArrayList.add(innerArrayList);
			    	bool = false;
	            }
		    }
		}
		else if(excelSheet.contains("_Input")) {
			
			outerArrayList = new ArrayList<ArrayList<String>>();
			innerArrayList = null;
			String inputCellValue = null;

			Iterator<Row> rows = sheet.iterator();
			int count = 0;
			
	        while (rows.hasNext()) {
	            row = (XSSFRow) rows.next();
	            Iterator<Cell> cells = row.cellIterator();
	            cell = row.getCell(count);
	            
	            if(row.getRowNum() > 0 && cell != null) {
	        		innerArrayList = new ArrayList<String>();
	            	
	        		while (cells.hasNext()) {
						Cell cell1 = cells.next();			
						inputCellValue = getCellData((XSSFCell) cell1);
						innerArrayList.add(inputCellValue);	
						bool = true;
	                }
	            }else if(cell == null) {break;}
	            if(bool == true) {
			    	outerArrayList.add(innerArrayList);
			    	bool = false;
	            }
	        }
		}
	    
	    try {
			wb.close();
			excelFile.close();
			Log.info("Excel File is closed");
			
		}catch (IOException e) {
			Log.info("Could not close the Excel File");
			e.printStackTrace();
		}
	    return outerArrayList;
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
			Log.info("No Cell DataType is available");
			e.printStackTrace();
		}
		return cellData;
	}
	
	
	public boolean isRowEmpty(Row row, int lastCellNo) {

	    for (int c = row.getFirstCellNum(); c < lastCellNo; c++) {
	        Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	        if (cell != null && cell.getCellTypeEnum() != CellType.BLANK) {
	            return false;
	        }
	    }
	    return true;
	}
	
	

}
