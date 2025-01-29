package it.olly.smartsheet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.smartsheet.api.Smartsheet;
import com.smartsheet.api.SmartsheetFactory;
import com.smartsheet.api.models.PagedResult;
import com.smartsheet.api.models.Sheet;

@SpringBootTest
class JavaSmartsheetTestApplicationTests {

	@Value("${smartsheet.apitoken}")
	private String apiToken;

	@Test
	void connectionTest() throws Exception {
		System.out.println("test connecting");

		// Initialize client
		Smartsheet smartsheet = SmartsheetFactory.createDefaultClient(apiToken);

		System.out.println("asking for listSheets");

		// List all sheets
		PagedResult<Sheet> sheets = smartsheet.sheetResources().listSheets();

		System.out.println("Found " + sheets.getTotalCount() + " sheets");

		long sheetId = sheets.getData().get(0).getId(); // Default to first sheet

		System.out.println("Loading sheet id: " + sheetId);

		// Load the entire sheet
		Sheet sheet = smartsheet.sheetResources().getSheet(sheetId);
		System.out.println("Loaded " + sheet.getTotalRowCount() + " rows from sheet: " + sheet.getName());
	}

}
