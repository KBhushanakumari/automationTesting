package utilsClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class JavaUtils {
	public static String readProperties(String property) throws IOException {
		Properties propertyFile = new Properties();
		String path = System.getProperty("user.dir") + "//src//test//resources//browser.properties";
		FileInputStream inputStream = new FileInputStream(path);
		propertyFile.load(inputStream);
		return propertyFile.getProperty(property);
	}
	
	public static String formatMonth(String month) {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put("Jan", "January");
		hash.put("Feb", "February");
		hash.put("Mar", "March");
		hash.put("Apr", "April");
		hash.put("May", "May");
		hash.put("Jun", "June");
		hash.put("Jul", "July");
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
