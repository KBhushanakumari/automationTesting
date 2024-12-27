package com.automationtesting.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class PracticeFormMethods {
	public static Object[][] readExcelData() throws IOException{
		String path = System.getProperty("user.dir") + "//src//test//resources//" + readProperties("registerformData");;
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
			driver.findElement(By.xpath(yearXpath.replace("year", dob[2]))).click();
			//Thread.sleep(2000);
			driver.findElement(By.xpath(monthXpath.replace("month", formatMonth(dob[1])))).click();
			driver.findElement(By.xpath(dayXpath.replace("day", formatDay(dob[0])))).click();
		}
	}
	
	public static void setSubjects(Object subjects, WebDriver driver) throws IOException {
		if(!subjects.toString().isEmpty()) {
			String subjectsXpath = readProperties("subjectsXpath");
			WebElement ele = driver.findElement(By.xpath(subjectsXpath));
			String[] sub = subjects.toString().split(",");
			for(String o : sub) {
				ele.sendKeys(o);
				ele.sendKeys(Keys.ENTER);
			}
		}
	}
	
	public static void setHobbies(Object hobbies, WebDriver driver) throws IOException {
		String hobbiesXpath = readProperties("hobbiesXpath");
		if(hobbies.toString() != "") {
			String path = "";
			if(hobbies.toString().contains("Music")) {
				path = hobbiesXpath.replace("hobbies", "Music");
			}
			if(hobbies.toString().contains("Reading")) {
				path = hobbiesXpath.replace("hobbies", "Reading");
			}
			if(hobbies.toString().contains("Sports")) {
				path = hobbiesXpath.replace("hobbies", "Sports");
			}
			WebElement ele = driver.findElement(By.xpath(path));
			ele.click();
		}
	}
	
	public static void chooseFile(String filepath, WebDriver driver) throws IOException {
		String choosefileXpath = readProperties("choosefileXpath");
		driver.findElement(By.xpath(choosefileXpath)).sendKeys(filepath);
	}
	
	public static void setCurrentAddress(Object address, WebDriver driver) throws IOException {
		String addressXpath = readProperties("addressXpath");
		WebElement ele = driver.findElement(By.xpath(addressXpath));
		ele.sendKeys(address.toString());
	}
	
	public static void SetState(String state, WebDriver driver) throws InterruptedException, IOException {
		String stateXpath = readProperties("stateXpath");
		driver.findElement(By.xpath(stateXpath)).click();
		String path = stateXpath.replace("Select State", state);
		driver.findElement(By.xpath(path)).click();
	}
	
	public static void SetCity(String city, String state, WebDriver driver) throws InterruptedException, IOException {
		if(!state.isEmpty()) {
			String cityXpath = readProperties("cityXpath");
			if(driver.findElement(By.xpath(cityXpath)).isDisplayed()) {
				driver.findElement(By.xpath(cityXpath)).click();
				String path = cityXpath.replace("Select City", city);
				if(driver.findElement(By.xpath(path)).isDisplayed()) {
					driver.findElement(By.xpath(path)).click();
				}
			}
		}
	}
	
	public static void ClickSubmit(WebDriver driver) throws IOException {
		String submitbuttonXpath = readProperties("submitbuttonXpath");
		driver.findElement(By.xpath(submitbuttonXpath)).click();
	}
	
	public static boolean passOrfail(String PositiveOrNegativecase, WebDriver driver) {
		WebElement currentPage = driver.findElement(By.xpath("//h1[contains(text(),'Practice Form')]"));
		if(PositiveOrNegativecase.equals("Negative")) {
			System.out.println("negative case");
			return currentPage.getText().equals("Practice Form");
		}
		if(PositiveOrNegativecase.equals("Positive")) {
			WebElement submissionPage = driver.findElement(By.xpath("//button[contains(text(),'Close')]"));
			driver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
			System.out.println("positive case");
			return true;
		}
		return false;
	}
	
	public static String formatMonth(String month) {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("Jan", "January");
		hash.put("Feb", "February");
		hash.put("Mar", "March");
		hash.put("Apr", "April");
		hash.put("May", "May");
		hash.put("Jun", "June");
		hash.put("Jal", "July");
		hash.put("Aug", "August");
		hash.put("Sep", "September");
		hash.put("Oct", "October");
		hash.put("Nov", "November");
		hash.put("Dec", "December");
		return hash.get(month);
	}
	
	public static String formatDay(String day) {
		if(day.charAt(0) == '0') {
			String formatedDay = Character.toString(day.charAt(1));
			return formatedDay;
		}
		return day;
	}
}
