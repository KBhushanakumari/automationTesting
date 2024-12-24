package com.automationtesting.demo;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
 
import javax.security.auth.Subject;
 
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
public class NewTest {
	public static WebDriver driver;
 
	@DataProvider(name = "exceldata")
	public Object[][] readData() throws IOException {
		String path = System.getProperty("user.dir") + "//src//test//resources//Data.xlsx";
		FileInputStream inputstream = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		Sheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();
		Object data[][] = new Object[rows-1][columns];
		for (int i = 1; i < rows; ++i) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < columns; ++j) {
				data[i-1][j] = row.getCell(j).toString();
			}
		}
		workbook.close();
		inputstream.close();
		return data;
	}
 
	@BeforeClass
	public void openBrowser() {
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/automation-practice-form");
		driver.manage().window().maximize();
	}
 
	@Test(dataProvider = "exceldata")
	public void register(Object firstname, Object lastname, Object email, Object gender, Object mobile,
			Object dateofBirth, Object subjects, Object hobbies, Object address, Object state, Object city) throws IOException, InterruptedException {
		setFirstname(firstname, "//input[@id='firstName']");
		setLastname(lastname, "//input[@id='lastName']");
		setUserEmail(email, "//input[@id='userEmail']");
		Thread.sleep(5000);
		setGender("//label[contains(text(), '" + gender + "')]");
		setUserNumber(mobile, "//input[@id='userNumber']");
		Thread.sleep(5000);
		setDateOfBirth(dateofBirth.toString(), "//input[@id='dateOfBirthInput']", "//option[text()='year']", "//option[text()='month']", "//div[text()='day']");
		setSubjects(subjects, "//input[@id='subjectsInput']");
		setHobbies(hobbies, "//label[contains(text(),'hobbies')]");
		setCurrentAddress(address, "//textarea[@id='currentAddress']");
		Thread.sleep(3000);
		SetState(state.toString(), "//div[contains(text(),'Select State')]");
		SetCity(city.toString(), "//div[contains(text(),'Select City')]");
		Thread.sleep(5000);
		ClickSubmit("//button[@id='submit']");
		Thread.sleep(3000);
	}
 
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	public static void setFirstname(Object firstname, String xPath) {
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.sendKeys(firstname.toString());
	}
	public static void setLastname(Object lastname, String xPath) {
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.sendKeys(lastname.toString());
	}
	public static void setUserEmail(Object email, String xPath) {
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.sendKeys(email.toString());
	}
	public static void setGender(String xPath) {
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.click();
	}
	public static void setUserNumber(Object mobile, String xPath) {
		WebElement ele = driver.findElement(By.xpath(xPath));
		ele.sendKeys(mobile.toString());
	}
	public static void setDateOfBirth(String dateofbirth, String xPath, String yearXpath, String monthXpath, String dayXpath) throws InterruptedException {
		//dd-mm-yyyy
		String[] dob = dateofbirth.split("-");
		driver.findElement(By.xpath(xPath)).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(yearXpath.replace("year", dob[2]))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(monthXpath.replace("month", dob[1]))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(dayXpath.replace("day", dob[0]))).click();
		Thread.sleep(5000);
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
}