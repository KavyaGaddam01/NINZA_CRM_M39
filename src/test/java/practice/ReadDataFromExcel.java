package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		//Create  java representation object of the physical file
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP\\Documents\\NINZA_CRM_M39.xlsx");
		
		//Open the excel in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
		//get the control sheet
		Sheet sh = wb.getSheet("Campaigns");
		
		//get control of row
		Row r = sh.getRow(1);
		
		//get control of cell
		Cell c = r.getCell(2);
		
		//read the data
		String CAMPAIGN_NAME = c.getStringCellValue();
		String TARGET_SIZE = wb.getSheet("Campaigns").getRow(1).getCell(3).getStringCellValue();
		
		System.out.println(CAMPAIGN_NAME);
		System.out.println(TARGET_SIZE);
		
		//close the workbook
		wb.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
