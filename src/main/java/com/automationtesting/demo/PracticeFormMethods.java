package com.automationtesting.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class PracticeFormMethods {
	public static Object[][] readExcelData() throws IOException{
		String path = System.getProperty("user.dir") + "//src//test//resources//RegisterData.xlsx";
		FileInputStream inputstream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		Sheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();
		Object data[][] = new Object[rows-1][columns];
		for (int i = 1; i < rows; ++i) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < columns; ++j) {
				Cell cell = row.getCell(j);
				if(cell != null && !cell.toString().isEmpty()) {
					data[i-1][j] = cell.toString();
				}
				else {
					data[i-1][j] = "";
				}
			}
		}
		workbook.close();
		inputstream.close();
		return data;
	}
	
	public static WebDriver launchBrowser() throws IOException {
		WebDriver driver;
		String browser = readProperties("browser");
		switch(browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		String link = readProperties("url");
		driver.get(link);
		return driver;
	}
	
	public static String readProperties(String property) throws IOException {
		Properties propertyFile = new Properties();
		String path = System.getProperty("user.dir") + "//src//test//resources//browser.properties";
		FileInputStream inputStream = new FileInputStream(path);
		propertyFile.load(inputStream);
		return propertyFile.getProperty(property);
	}
	
	public static void setFirstname(Object firstname, WebDriver driver) throws IOException {
		String xPath = readProperties("firstnameXpath");
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.sendKeys(firstname.toString());
	}
	
	public static void setLastname(Object lastname, WebDriver driver) throws IOException {
		String xPath = readProperties("lastnameXpath");
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.sendKeys(lastname.toString());
	}
	
	public static void setUserEmail(Object email, WebDriver driver) throws IOException {
		String xPath = readProperties("emailXpath");
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.sendKeys(email.toString());
	}
	
	public static void setGender(Object gender, WebDriver driver) throws IOException {
		String xPath = readProperties("genderXpath");
		WebElement ele = driver.findElement(By.xpath(xPath.replace("gender", gender.toString())));
		ele.click();
	}
	
	public static void setUserNumber(Object mobile, WebDriver driver) throws IOException {
		String xPath = readProperties("mobileXpath");
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.sendKeys(mobile.toString());
	}
	
	public static void setDateOfBirth(String dateofbirth, WebDriver driver) throws IOException, InterruptedException {
		//dd-mm-yyyy
		String dateofbirthXpath = readProperties("dateOfBirthInputXpath");
		String yearXpath = readProperties("yearXpath");
		String monthXpath = readProperties("monthXpath");
		String dayXpath = readProperties("dayXpath");
		if(!dateofbirth.isEmpty()) {
			String[] dob = dateofbirth.split("-");
			driver.findElement(By.xpath(dateofbirthXpath)).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(yearXpath.replace("year", dob[2]))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(monthXpath.replace("month", dob[1]))).click();
			driver.findElement(By.xpath(dayXpath.replace("day", dob[0]))).click();
		}
	}
}
