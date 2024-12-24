package com.automationtesting.demo;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewTest {
	public static WebDriver driver;
	public static void main(String[] args) throws IOException {
		openBrowser();
		register();
		closeBrowser();
	}
	public static Object[][] readData() throws IOException {
		String path = System.getProperty("user.dir") + "//src//test//resources//RegisterData.xlsx";
		FileInputStream inputstream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		Sheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();
		Object data[][] = new Object[rows][columns];
		for(int i = 0; i < rows-1; ++i) {
			Row row = sheet.getRow(i);
			for(int j = 0; j < columns; ++j) {
				data[i][j] = row.getCell(j);
			}
		}
		workbook.close();
		inputstream.close();
		return data;
	}
	
	public static void openBrowser() {
		driver = new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Register.html");
	}
	
	public static void register() throws IOException {
		System.out.println("test");
		/*
		setFirstName(firstname, firstnameXpath);
		setLastName(lastname, lastnameXpath);
		setAddress(address, addressXpath);
		setEmailAddress(emailaddress, emailaddressXpath);
		setPhone(phone, phoneXpath);
		setGender(gender, genderXpath);
		selectHobbiesCricket(hobbiesCricketXpath);
		selectHobbiesMovies(hobbiesMoviesXpath);
		selectHobbiesHockey(hobbiesHockeyXpath);
		selectLanguages(languages, languagesXpath);
		//deselectLanguages(languagesDeselect, languagesXpath);
		setSkills(skills, skillsXpath);
		//setCountry(country, countryXpath);
		setSelectCountry(selectcountry, selectcountryXpath);
		setDateOfBirth(year, yearXpath, month, monthXpath, day, dayXpath);
		setPassword(password, passwordXpath);
		setConfirmPassword(confirmpassword, confirmpasswordXpath);
		uploadFile(filePath, choosefileXpath);
		clickSubmit(submitXpath);
		clickRefresh(refrestXpath);
		*/
	}
	
	public static void closeBrowser() {
		driver.quit();
	}
}
