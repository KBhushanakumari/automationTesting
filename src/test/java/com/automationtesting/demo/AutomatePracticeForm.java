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
	}
	
	@Test(dataProvider = "exceldata")
	public void register(Object firstname, Object lastname, Object email, Object gender, Object mobile,
			Object dateofBirth, Object subjects, Object hobbies, Object file, Object address, Object state, Object city, String PositiveOrNegativecase) throws IOException, InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='50%'");
		setFirstname(firstname, driver);
		setLastname(lastname, driver);
		setUserEmail(email, driver);
		Thread.sleep(2000);
		setGender(gender, driver);
		setUserNumber(mobile, driver);
		Thread.sleep(2000);
		setDateOfBirth(dateofBirth.toString(), driver);
		setSubjects(subjects, "//input[@id='subjectsInput']");
		setHobbies(hobbies, "//label[contains(text(),'hobbies')]");
		String filepath = System.getProperty("user.dir") + file.toString();
		chooseFile(filepath,"//input[@id='uploadPicture']");
		setCurrentAddress(address, "//textarea[@id='currentAddress']");
		Thread.sleep(2000);
		SetState(state.toString(), "//div[contains(text(),'Select State')]");
		SetCity(city.toString(), "//div[contains(text(),'Select City')]");
		Thread.sleep(3000);
		ClickSubmit("//button[@id='submit']");
		Thread.sleep(3000);
		assertEquals(true,passOrfail(PositiveOrNegativecase));
		Thread.sleep(3000);
		driver.navigate().refresh();
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	public static void setSubjects(Object subjects, String xPath) {
		WebElement ele = driver.findElement(By.xpath(xPath));
		String[] sub = subjects.toString().split(",");
		for(String o : sub) {
			ele.sendKeys(o);
			ele.sendKeys(Keys.ENTER);
		}
	}
	public static void setHobbies(Object hobbies, String xPath) {
		String path = "";
		if(hobbies.toString().contains("Music")) {
			path = xPath.replace("hobbies", "Music");
		}
		if(hobbies.toString().contains("Reading")) {
			path = xPath.replace("hobbies", "Reading");
		}
		if(hobbies.toString().contains("Sports")) {
			path = xPath.replace("hobbies", "Sports");
		}
		WebElement ele = driver.findElement(By.xpath(path));
		ele.click();
	}
	public static void chooseFile(String filepath, String xPath) {
		driver.findElement(By.xpath(xPath)).sendKeys(filepath);
	}
	public static void setCurrentAddress(Object address, String xPath) {
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.sendKeys(address.toString());
	}
	public static void SetState(String state, String xPath) throws InterruptedException {
		driver.findElement(By.xpath(xPath)).click();
		String path = xPath.replace("Select State", state);
		Thread.sleep(2000);
		driver.findElement(By.xpath(path)).click();
	}
	public static void SetCity(String city, String xPath) throws InterruptedException {
		driver.findElement(By.xpath(xPath)).click();
		String path = xPath.replace("Select City", city);
		Thread.sleep(2000);
		driver.findElement(By.xpath(path)).click();
	}
	public static void ClickSubmit(String xPath) {
		driver.findElement(By.xpath(xPath)).click();
	}
	public static void CloseSubmissionWindow(String xPath) {
		driver.findElement(By.xpath(xPath)).click();
	}
	public static boolean passOrfail(String PositiveOrNegativecase) {
		WebElement currentPage = driver.findElement(By.xpath("//h1[contains(text(),'Practice Form')]"));
		if(PositiveOrNegativecase.equals("Negative")) {
			System.out.println("negative case");
			return currentPage.getText().equals("Practice Form");
		}
		if(PositiveOrNegativecase.equals("Positive")) {
			WebElement submissionPage = driver.findElement(By.xpath("//button[contains(text(),'Close')]"));
			driver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
			System.out.println("clicked close");
			return true;
		}
		return false;
	}
}