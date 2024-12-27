package com.automationtesting.demo;
 
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
public class AutomatePracticeForm extends PracticeFormMethods{
	public static WebDriver driver;
	@DataProvider(name = "exceldata")
	public Object[][] readData() throws IOException {
		Object[][] formData = readExcelData();
		return formData;
	}
	
	@BeforeClass
	public void openBrowser() throws IOException {
		driver = launchBrowser();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().refresh();
		js.executeScript("document.body.style.zoom='50%'");
	}
	
	@Test(dataProvider = "exceldata")
	public void register(Object firstname, Object lastname, Object email, Object gender, Object mobile,
			Object dateofBirth, Object subjects, Object hobbies, Object file, Object address, Object state, Object city, String PositiveOrNegativecase) throws IOException, InterruptedException {
		Thread.sleep(2000);
		setFirstname(firstname, driver);
		setLastname(lastname, driver);
		setUserEmail(email, driver);
		setGender(gender, driver);
		setUserNumber(mobile, driver);
		setDateOfBirth(dateofBirth.toString(), driver);
		setSubjects(subjects, driver);
		setHobbies(hobbies, driver);
		String filepath = System.getProperty("user.dir") + file.toString();
		chooseFile(filepath,driver);
		setCurrentAddress(address, driver);
		SetState(state.toString(), driver);
		SetCity(city.toString(), state.toString(), driver);
		ClickSubmit(driver);
		assertEquals(true,passOrfail(PositiveOrNegativecase, driver));
	}
	
	@AfterMethod
	public void checkAssert() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().refresh();
		js.executeScript("document.body.style.zoom='50%'");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}