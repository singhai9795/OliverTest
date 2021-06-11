package com.pos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.pos.base.TestBase;

public class TestUtill extends TestBase{

	public static long IMPLICIT_WAIT = 20;
	public String testDataFilePath = "/Users/abhi/Desktop/Javatest/OliverTest/src/main/java/com/pos/testdata/loginData.xlsx";
    static Workbook loginWorkbook = null;

	public void readExcel(String sheetName){

//	    File file = new File(testDataFilePath);
	    
	    //Create an object of FileInputStream class to read excel file
	    FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(testDataFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    try {
			loginWorkbook = WorkbookFactory.create(inputStream);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    

	    //Read sheet inside the workbook by its name
	    Sheet loginSheet = loginWorkbook.getSheet(sheetName);
	    System.out.println(loginSheet);

	    //Find number of rows in excel file
	    int rowCount = loginSheet.getLastRowNum()-loginSheet.getFirstRowNum();
	    
	    //Create a loop over all the rows of excel file to read it

//	    for (int i = 0; i < rowCount+1; i++) {
//
//	        Row row = loginSheet.getRow(i);
//
//	        //Create a loop to print cell values in a row
//
//	        for (int j = 0; j < row.getLastCellNum(); j++) {
//
//	            //Print Excel data in console
//
//	            System.out.print(row.getCell(j).getStringCellValue()+"|| ");
//	            Object data[i][j] = new Object[row.getCell(j).getStringCellValue()];
//
//	        }
//
//	        System.out.println();
//	    } 

	  }
	
	public static void main(String args[]) throws IOException {
		TestUtill obj = new TestUtill();
		obj.readExcel("login");
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + getCurrentDateTime() + ".png"));
	}
	
	public static String takeScreenshotForExtentReport(String screenshotName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/Reports/ExtentScreenshot" + getCurrentDateTime() + ".png"));
		String destination = currentDir + "/ExtentScreenshot/" + getCurrentDateTime()+screenshotName + ".png";
		return destination;
	}
	
	public static String getCurrentDateTime() {
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");
		
		//get current date time with Date()
		Date date = new Date();
		 
		// Now format the date
		String date1= dateFormat.format(date);
		return date1;
	}
}
