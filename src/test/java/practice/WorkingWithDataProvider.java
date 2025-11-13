package practice;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericutilities.ExcelFileUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class WorkingWithDataProvider {

	@Test(dataProvider = "loginDetails")
	public void login(String username,String password) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp=new LoginPage(driver);
		lp.login("http://49.249.28.218:8098/", username, password);
		
		HomePage hp=new HomePage(driver);
		hp.logout();
		driver.quit();
	}
	
	@DataProvider
	public Object[][] loginDetails() throws EncryptedDocumentException, IOException{
		
		ExcelFileUtility eLib=new ExcelFileUtility();
		int rowCount = eLib.getRowCount("DataProvider");
		
		Object[][] objArr=new Object[rowCount][2];
		
		for(int r=1;r<=rowCount;r++) {
			objArr[r-1][0]=eLib.readDataFromExcelFile("DataProvider", r,0 );
			objArr[r-1][1]=eLib.readDataFromExcelFile("DataProvider", r, 1);
		}
		
			
		return objArr;
		
	}
}
















