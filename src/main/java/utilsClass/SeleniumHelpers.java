package utilsClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumHelpers {
	public static WebDriver launchBrowser() throws IOException {
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
}
