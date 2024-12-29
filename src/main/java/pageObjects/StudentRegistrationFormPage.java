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
	
	private WebElement genderRadioButton(Object gender) throws IOException {
		String xPath = JavaUtils.readProperties("genderXpath");
		return driver.findElement(By.xpath(xPath.replace("gender", gender.toString())));
	}
	
	private WebElement yearOfBirthDropdown(String year) throws IOException {
		String yearXpath = JavaUtils.readProperties("yearXpath");
		return driver.findElement(By.xpath(yearXpath.replace("year", year)));
	}
	
	private WebElement monthOfBirthDropdown(String month) throws IOException {
		String monthXpath = JavaUtils.readProperties("monthXpath");
		return driver.findElement(By.xpath(monthXpath.replace("month", JavaUtils.formatMonth(month))));
	}

	private WebElement dayOfBirthDiv(String day) throws IOException {
		String dayXpath = JavaUtils.readProperties("dayXpath");
		return driver.findElement(By.xpath(dayXpath.replace("day", JavaUtils.formatDay(day))));
	}
	
	private WebElement hobbiesCheckbox(String hobby) throws IOException {
		String hobbiesXpath = JavaUtils.readProperties("hobbiesXpath");
		return driver.findElement(By.xpath(hobbiesXpath.replace("hobbies", hobby)));
	}
	
	private WebElement stateDropdown(String state) throws IOException {
		String stateXpath = JavaUtils.readProperties("stateXpath");
		return driver.findElement(By.xpath(stateXpath.replace("Select State", state)));
	}
	
	private WebElement cityDropdown(String city) throws IOException {
		String cityXpath = JavaUtils.readProperties("cityXpath");
		return driver.findElement(By.xpath(cityXpath.replace("Select City", city)));
	}
	//constructor
	public StudentRegistrationFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void setFirstname(Object firstname) {
		firstNameField.sendKeys(firstname.toString());
	}
	
	public void setLastname(Object lastname) {
		lastNameField.sendKeys(lastname.toString());
	}
	
	public void setUserEmail(Object email) {
		emailField.sendKeys(email.toString());
	}
	
	public void setGender(Object gender) throws IOException {
		genderRadioButton(gender).click();
	}
	
	public void setUserNumber(Object mobile) {
		mobileField.sendKeys(mobile.toString());
	}
	
	public void setDateOfBirth(String dateofbirth) throws IOException {
		//dd-mm-yyyy
		if(!dateofbirth.isEmpty()) {
			dateOfBirthField.click();
			String[] dob = dateofbirth.split("-");
			yearOfBirthDropdown(dob[2]).click();
			monthOfBirthDropdown(dob[1]).click();
			dayOfBirthDiv(dob[0]).click();
		}
	}
	
	public void setSubjects(Object subjects) {
		if(!subjects.toString().isEmpty()) {
			String[] sub = subjects.toString().split(",");
			for(String o : sub) {
				subjectsField.sendKeys(o);
				subjectsField.sendKeys(Keys.ENTER);
			}
		}
	}
	
	public void setHobbies(Object hobbies) throws IOException {
		if(hobbies.toString() != "") {
			if(hobbies.toString().contains("Music")) {
				hobbiesCheckbox("Music").click();
			}
			if(hobbies.toString().contains("Reading")) {
				hobbiesCheckbox("Reading").click();
			}
			if(hobbies.toString().contains("Sports")) {
				hobbiesCheckbox("Sports").click();
			}
		}
	}
	
	public void chooseFile(String filepath) throws IOException {
		chooseFilebutton.sendKeys(filepath);
	}
	
	public void setCurrentAddress(Object address) throws IOException {
		addressField.sendKeys(address.toString());
	}
	
	public void SetState(String state) throws InterruptedException, IOException {
		selectstateField.click();
		stateDropdown(state).click();
	}
	
	public void SetCity(String city, String state) throws IOException {
		if(!state.isEmpty()) {
			if(selectcityField.isDisplayed()) {
				selectcityField.click();
				if(cityDropdown(city).isDisplayed()) {
					cityDropdown(city).click();
				}
			}
		}
	}
	
	public void ClickSubmit() {
		submitButton.click();
	}
	
	public boolean passOrfail(String PositiveOrNegativecase) throws IOException {
		if(PositiveOrNegativecase.equals("Negative")) {
			System.out.println("negative case");
			return practiceFormHeading.getText().equals("Practice Form");
		}
		if(PositiveOrNegativecase.equals("Positive")) {
			closeButton.click();
			System.out.println("positive case");
			return true;
		}
		return false;
	}
	
	public void register(Object firstname, Object lastname, Object email, Object gender, Object mobile,
			Object dateofBirth, Object subjects, Object hobbies, Object file, Object address, Object state, Object city, String PositiveOrNegativecase) throws IOException, InterruptedException {
		Thread.sleep(2000);
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
