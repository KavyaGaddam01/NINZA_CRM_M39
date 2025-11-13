package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

public class CreateCampaignWithMandatoryFields {

	public static void main(String[] args) throws InterruptedException, IOException {

		// Read data from properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\QSP\\Documents\\CommondataM39.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("URL");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");
		
		//read test script data from excel file
		FileInputStream fis1=new FileInputStream("C:\\Users\\QSP\\Documents\\NINZA_CRM_M39.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String CAMPAIGN_NAME = wb.getSheet("Campaigns").getRow(1).getCell(2).getStringCellValue();
		String TARGET_SIZE = wb.getSheet("Campaigns").getRow(1).getCell(3).getStringCellValue();
		wb.close();


		// Launch the browser
		ChromeOptions settings = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs);

		WebDriver driver = null;

		if (BROWSER.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver(settings);
		else if (BROWSER.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Login
		LoginPage loginPage=new LoginPage(driver);
		loginPage.login(URL, USERNAME, PASSWORD);

		// Create campaign with Mandatory Fields
		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage=new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		WebElement targetSize = createCampaignPage.getTargetSizeTF();
		targetSize.clear();
		targetSize.sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

		// verification
		HomePage  homePage=new HomePage(driver);
		WebElement toastMsg = homePage.getToastMsg();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		String msg = toastMsg.getText();
		System.out.println(msg);
		if (msg.contains("Successfully Added")) {
			System.out.println("Campaign Created");
		} else {
			System.out.println("Campaign Not Created");
		}
		homePage.getCloseToastMsgBtn().click();

		// Logout
		homePage.logout();

		// Close the browser
		driver.quit();

	}

}
