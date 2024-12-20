package testNG;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class SampleTest {
	
	@Test(invocationCount = 1)
	public void Demo() throws IOException{
		System.out.println("TestNG Welcome...!");
		Properties propertyFile = new Properties();
		String path = System.getProperty("user.dir") + "//src//test//resources//browser.properties";
		FileInputStream inputStream = new FileInputStream(path);
		propertyFile.load(inputStream);
		System.out.println(propertyFile.getProperty("browser"));
	}
	
}
