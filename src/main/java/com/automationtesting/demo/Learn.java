package com.automationtesting.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Learn {
	
	public static Object[][] readData() throws IOException {
		String path = System.getProperty("user.dir") + "//src//test//resources//Data.xlsx";
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
				data[i-1][j] = row.getCell(j).toString();
			}
		}
		workbook.close();
		inputstream.close();
		return data;
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
	}
}
