package genericutilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String readDataFromExcelFile(String sheetName,int rowNum,int cellNmum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis1 = new FileInputStream("./src/test/resources/NINZA_CRM_M39.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String data=wb.getSheet(sheetName).
				getRow(rowNum).getCell(cellNmum).getStringCellValue();
		wb.close();
		
		return data;
		
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis1 = new FileInputStream("./src/test/resources/NINZA_CRM_M39.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		
		wb.close();
		
		return rowCount;
		
	}
	
}
