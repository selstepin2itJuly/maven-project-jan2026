package utitities;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtilities {

	public static String readCellData(String filePath, String sheetName, int rowNum, int cellNum) {
		String cellValue = "";
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = WorkbookFactory.create(fis)) {
			// WorkbookFactory
			// handles
			Sheet sheet = workbook.getSheet(sheetName); // Get the first sheet (0-based index)
			Row row = sheet.getRow(rowNum); // Get the desired row (0-based index)

			if (row != null) {
				Cell cell = row.getCell(cellNum); // Get the desired cell (0-based index)

				if (cell != null) {
					// Use DataFormatter to get the cell value as a formatted string,
					// handling different cell types (numeric, string, boolean, etc.)
					DataFormatter formatter = new DataFormatter();
					cellValue = formatter.formatCellValue(cell);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellValue;
	}

	public static String readCellData(String filePath, int rowNum, int cellNum) {
		String cellValue = "";
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = WorkbookFactory.create(fis)) {
			// WorkbookFactory
			// handles
			Sheet sheet = workbook.getSheetAt(0); // Get the first sheet (0-based index)
			Row row = sheet.getRow(rowNum); // Get the desired row (0-based index)

			if (row != null) {
				Cell cell = row.getCell(cellNum); // Get the desired cell (0-based index)

				if (cell != null) {
					// Use DataFormatter to get the cell value as a formatted string,
					// handling different cell types (numeric, string, boolean, etc.)
					DataFormatter formatter = new DataFormatter();
					cellValue = formatter.formatCellValue(cell);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellValue;
	}

	public static String getFilePath(String nameOfFile) {
		// Create a Path object using a relative path
		Path relativePath = Paths.get("src/test/resources/testdata/" + nameOfFile);

		// Get the absolute path
		Path absolutePath = relativePath.toAbsolutePath();
		// System.out.println("toAbsolutePath(): " + absolutePath.toString());

		return absolutePath.toString();
	}

}
