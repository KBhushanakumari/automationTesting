package utilsClass;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelpers {
	public static Object[][] readExcelData() throws IOException{
		String path = System.getProperty("user.dir") + "//src//test//resources//" + JavaUtils.readProperties("registerformData");;
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
}