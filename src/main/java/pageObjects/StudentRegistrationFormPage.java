package pageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilsClass.JavaUtils;
import utilsClass.SeleniumHelpers;
import utilsClass.TestNGHelpers;

public class StudentRegistrationFormPage {
	WebDriver driver;
	
	@FindBy(xpath = "//input[@id='firstName']")
	private WebElement firstNameField;
	
	@FindBy(xpath = "//input[@id='lastName']")
	private WebElement lastNameField;
	
	@FindBy(xpath = "//input[@id='userEmail']")
	private WebElement emailField;
	
	@FindBy(xpath = "//input[@id='userNumber']")
	private WebElement mobileField;
	
	@FindBy(xpath = "//input[@id='dateOfBirthInput']")
	private WebElement dateOfBirthField;
	
	@FindBy(xpath = "//input[@id='subjectsInput']")
	private WebElement subjectsField;
	
	@FindBy(xpath = "//input[@id='uploadPicture']")
	private WebElement chooseFilebutton;
	
	@FindBy(xpath = "//textarea[@id='currentAddress']")
	private WebElement addressField;
	
	@FindBy(xpath = "//div[contains(text(),'Select State')]")
	private WebElement selectstateField;
	
	@FindBy(xpath = "//div[contains(text(),'Select City')]")
	private WebElement selectcityField;
	
	@FindBy(xpath = "//button[@id='submit']")
	private WebElement submitButton;
	
	@FindBy(xpath = "//h1[contains(text(),'Practice Form')]")
	private WebElement practiceFormHeading;
	
	@FindBy(xpath = "//button[contains(text(),'Close')]")
	private WebElement closeButton;
	
	private WebElement genderRadioButton(Object gender) {
		String xPath = JavaUtils.readProperties("genderXpath");
		return driver.findElement(By.xpath(xPath.replace("gender", gender.toString())));
	}
	
	private WebElement yearOfBirthDropdown(String year) {
		String yearXpath = JavaUtils.readProperties("yearXpath");
		return driver.findElement(By.xpath(yearXpath.replace("year", year)));
	}
	
	private WebElement monthOfBirthDropdown(String month) {
		String monthXpath = JavaUtils.readProperties("monthXpath");
		return driver.findElement(By.xpath(monthXpath.replace("month", JavaUtils.formatMonth(month))));
	}

	private WebElement dayOfBirthDiv(String day) {
		String dayXpath = JavaUtils.readProperties("dayXpath");
		return driver.findElement(By.xpath(dayXpath.replace("day", JavaUtils.formatDay(day))));
	}
	
	private WebElement hobbiesCheckbox(String hobby) {
		String hobbiesXpath = JavaUtils.readProperties("hobbiesXpath");
		return driver.findElement(By.xpath(hobbiesXpath.replace("hobbies", hobby)));
	}
	
	private WebElement stateDropdown(String state) {
		String stateXpath = JavaUtils.readProperties("stateXpath");
		return driver.findElement(By.xpath(stateXpath.replace("Select State", state)));
	}
	
	private WebElement cityDropdown(String city) {
		String cityXpath = JavaUtils.readProperties("cityXpath");
		return driver.findElement(By.xpath(cityXpath.replace("Select City", city)));
	}
	//constructor
	public StudentRegistrationFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void setFirstname(Object firstname) {
		SeleniumHelpers.sendData(firstNameField, firstname.toString());
	}
	
	public void setLastname(Object lastname) {
		SeleniumHelpers.sendData(lastNameField, lastname.toString());
	}
	
	public void setUserEmail(Object email) {
		SeleniumHelpers.sendData(emailField, email.toString());
	}
	
	public void setGender(Object gender) {
		SeleniumHelpers.click(genderRadioButton(gender));
	}
	
	public void setUserNumber(Object mobile) {
		SeleniumHelpers.sendData(mobileField, mobile.toString());
	}
	
	public void setDateOfBirth(String dateofbirth) {
		//dd-mm-yyyy
		if(!dateofbirth.isEmpty()) {
			SeleniumHelpers.click(dateOfBirthField);
			String[] dob = dateofbirth.split("-");
			SeleniumHelpers.click(yearOfBirthDropdown(dob[2]));
			SeleniumHelpers.click(monthOfBirthDropdown(dob[1]));
			SeleniumHelpers.click(dayOfBirthDiv(dob[0]));
		}
	}
	
	public void setSubjects(Object subjects) {
		if(!subjects.toString().isEmpty()) {
			String[] sub = subjects.toString().split(",");
			for(String o : sub) {
				SeleniumHelpers.sendData(subjectsField, o);
				SeleniumHelpers.sendData(subjectsField, Keys.ENTER);
			}
		}
	}
	
	public void setHobbies(Object hobbies) {
		if(hobbies.toString() != "") {
			if(hobbies.toString().contains("Music")) {
				SeleniumHelpers.click(hobbiesCheckbox("Music"));
			}
			if(hobbies.toString().contains("Reading")) {
				SeleniumHelpers.click(hobbiesCheckbox("Reading"));
			}
			if(hobbies.toString().contains("Sports")) {
				SeleniumHelpers.click(hobbiesCheckbox("Sports"));
			}
		}
	}
	
	public void chooseFile(String filepath) {
		SeleniumHelpers.sendData(chooseFilebutton, filepath);
	}
	
	public void setCurrentAddress(Object address) {
		SeleniumHelpers.sendData(addressField, address.toString());
	}
	
	public void SetState(String state) {
		SeleniumHelpers.click(selectstateField);
		SeleniumHelpers.click(stateDropdown(state));
	}
	
	public void SetCity(String city, String state) {
		if(!state.isEmpty()) {
				SeleniumHelpers.click(selectcityField);
				SeleniumHelpers.click(cityDropdown(city));
		}
	}
	
	public void ClickSubmit() {
		SeleniumHelpers.click(submitButton);
	}
	
	public boolean passOrfail(String PositiveOrNegativecase) {
		if(PositiveOrNegativecase.equals("Negative")) {
			System.out.println("negative case");
			return practiceFormHeading.getText().equals("Practice Form");
		}
		if(PositiveOrNegativecase.equals("Positive")) {
			SeleniumHelpers.waitToClick(driver, closeButton);
			SeleniumHelpers.click(closeButton);
			System.out.println("positive case");
			return true;
		}
		return false;
	}
	
	public void register(Object firstname, Object lastname, Object email, Object gender, Object mobile,
			Object dateofBirth, Object subjects, Object hobbies, Object file, Object address, Object state, Object city, String PositiveOrNegativecase) {
		SeleniumHelpers.waitToLocateElement(driver, firstNameField);
		setFirstname(firstname);
		setLastname(lastname);
		setUserEmail(email);
		setGender(gender);
		setUserNumber(mobile);
		setDateOfBirth(dateofBirth.toString());
		setSubjects(subjects);
		setHobbies(hobbies);
		String filepath = System.getProperty("user.dir") + file.toString();
		chooseFile(filepath);
		setCurrentAddress(address);
		SetState(state.toString());
		SetCity(city.toString(), state.toString());
		ClickSubmit();
		assertEquals(true,passOrfail(PositiveOrNegativecase));
	}
}
