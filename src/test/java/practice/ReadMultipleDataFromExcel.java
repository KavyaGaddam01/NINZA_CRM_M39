package practice;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		FileInputStream fis=new FileInputStream("C:\\Users\\QSP\\Documents\\NINZA_CRM_M39.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("Practice");
		
		int rowCount = sh.getLastRowNum();
		
		for( int i=1;i<=rowCount;i++) {
			String data = sh.getRow(i).getCell(0).getStringCellValue();
			System.out.println(data);
		}
		
		wb.close();
	}
	

}
