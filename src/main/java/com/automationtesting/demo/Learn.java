package com.automationtesting.demo;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Learn {
	public static Object[][] readData() throws IOException {
		String path = System.getProperty("user.dir") + "//src//test//resources//RegisterData.xlsx";
		FileInputStream inputstream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		Sheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data = new Object[rows][columns];
		for(int i = 0; i < rows; ++i) {
			Row row = sheet.getRow(i);
			for(int j = 0; j < columns; ++j) {
				data[i][j] = row.getCell(j).getStringCellValue();
			}
		}
		workbook.close();
		inputstream.close();
		return data;
	}
	public static void main(String[] args) throws IOException {
		readData();
	}
}
