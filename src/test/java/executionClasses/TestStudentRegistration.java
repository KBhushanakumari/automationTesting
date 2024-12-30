package executionClasses;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.StudentRegistrationFormPage;
import utilsClass.ExcelHelpers;
import utilsClass.SeleniumHelpers;

public class TestStudentRegistration {
	WebDriver driver;
	
	@DataProvider(name = "exceldata")
	public Object[][] readData() throws IOException {
		Object[][] formData = ExcelHelpers.readExcelData();
		return formData;
	}
	
	@BeforeClass
	public void openBrowser() throws IOException {
		driver = SeleniumHelpers.launchBrowser();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().refresh();
		js.executeScript("document.body.style.zoom='50%'");
	}
	
	@Test(dataProvider = "exceldata")
	public void studentRegistration(Object firstname, Object lastname, Object email, Object gender, Object mobile,
			Object dateofBirth, Object subjects, Object hobbies, Object file, Object address, Object state, Object city, String PositiveOrNegativecase) throws IOException, InterruptedException {
		
		StudentRegistrationFormPage form = new StudentRegistrationFormPage(driver);
		form.register(firstname, lastname, email, gender, mobile, dateofBirth, subjects, hobbies, file, address, state, city, PositiveOrNegativecase);
	}
	
	@AfterMethod
	public void refreshPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().refresh();
		js.executeScript("document.body.style.zoom='50%'");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
