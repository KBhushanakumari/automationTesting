package utilsClass;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelpers {
	public static WebDriver launchBrowser() {
		WebDriver driver;
		String browser = JavaUtils.readProperties("browser");
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
		String link = JavaUtils.readProperties("url");
		driver.get(link);
		return driver;
	}
	
	public static void click(WebElement element) {
		if(element.isDisplayed() & element.isEnabled()) {
			element.click();
		}
		else {
			System.out.println(element.getText() + element.getTagName());
			System.out.println("Not Clikable!!");
		}
	}
	
	public static void sendData(WebElement element, String data) {
		if(element.isDisplayed() & element.isEnabled()) {
			element.clear();
			element.sendKeys(data);
		}
		else {
			System.out.println("Cannot send data to webelement!!!");
		}
	}

	public static void sendData(WebElement subjectsField, Keys enter) {
		subjectsField.sendKeys(enter);
	}
	
	public static void waitToClick(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitToLocateElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
