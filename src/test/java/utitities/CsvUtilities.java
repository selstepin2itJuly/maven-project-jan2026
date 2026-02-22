package utitities;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvUtilities {

	public static String getFilePath(String nameOfFile) {
		// Create a Path object using a relative path
		Path relativePath = Paths.get("src/test/resources/testdata/" + nameOfFile);

		// Get the absolute path
		Path absolutePath = relativePath.toAbsolutePath();
		// System.out.println("toAbsolutePath(): " + absolutePath.toString());

		return absolutePath.toString();
	}

	public static String readSpecificCell(String filePath, int targetRow, int targetCol)
			throws IOException, CsvValidationException {

		String cellValue = null;
		// Use try-with-resources to ensure the reader is closed
		try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
			String[] nextRecord;
			int currentRow = 0;

			while ((nextRecord = reader.readNext()) != null) { // Read each row
				if (currentRow == targetRow) {
					// Check if the target column exists in the current row
					if (targetCol >= 0 && targetCol < nextRecord.length) {
						cellValue = nextRecord[targetCol];
						break; // Stop after finding the cell
					}
				}
				currentRow++;
			}
		}
		return cellValue;
	}
}
