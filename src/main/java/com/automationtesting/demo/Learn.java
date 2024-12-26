package com.automationtesting.demo;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Learn extends PracticeFormMethods{
	
	public static Object[][] readData() throws IOException {
		String path = System.getProperty("user.dir") + "//src//test//resources//RegisterData.xlsx";
		FileInputStream inputstream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		Sheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(rows + " " + columns);
		Object[][] data = new Object[rows-1][columns];
		for(int i = 1; i < rows; ++i) {
			Row row = sheet.getRow(i);
			for(int j = 0; j < columns; ++j) {
				Cell cell = row.getCell(j);
				System.out.print("[" + (i-1) + "][" + j + "]");
				if(cell != null && !cell.toString().isEmpty()) {
					data[i-1][j] = cell.toString();
				}
				else {
					data[i-1][j] = "";
				}
				System.out.println(data[i-1][j]);
			}
		}
		workbook.close();
		inputstream.close();
		return data;
//		Object[][] formData = readExcelData();
//		return formData;
	}
	public static void main(String[] args) throws IOException {
		Object[][] info = readData();
		System.out.println(info.length + " " + info[0].length);
		for(int i = 0; i < info.length; ++i) {
			for(int j = 0; j < info[0].length; ++j) {
				Object temp = info[i][j];
				System.out.println(temp + " ");
				if(temp.equals("F")) {
					System.out.println("F test");
				}
			}
			System.out.println();
		}
		
//		WebDriver driver = new ChromeDriver();
//		String link = readProperties("url");
//		System.out.println(link);
//		driver = launchBrowser(link);
//		driver.findElement(By.xpath(link));
	}
}
