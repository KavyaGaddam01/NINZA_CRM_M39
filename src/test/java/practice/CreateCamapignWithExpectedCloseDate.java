package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import objectrepository.CampaignsPage;

public class CreateCamapignWithExpectedCloseDate {

	public static void main(String[] args) throws IOException {

		// Read data from properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\QSP\\Documents\\CommondataM39.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("URL");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");

		// read test script data from excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\QSP\\Documents\\NINZA_CRM_M39.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String CAMPAIGN_NAME = wb.getSheet("Campaigns").getRow(7).getCell(2).getStringCellValue();
		String TARGET_SIZE = wb.getSheet("Campaigns").getRow(7).getCell(3).getStringCellValue();
		wb.close();

		// Generate date after 30 days
		Date d = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(d);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String requiredDate = sim.format(cal.getTime());

		// Launch the browser
		WebDriver driver = null;

		if (BROWSER.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (BROWSER.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Login
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		// Create campaign with Mandatory Fields
		CampaignsPage campaignsPage=new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
		WebElement targetSize = driver.findElement(By.name("targetSize"));
		targetSize.clear();
		targetSize.sendKeys(TARGET_SIZE);
		driver.findElement(By.name("expectedCloseDate")).sendKeys(requiredDate);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// verification
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		String msg = toastMsg.getText();
		System.out.println(msg);
		if (msg.contains("Successfully Added")) {
			System.out.println("Campaign Created");
		} else {
			System.out.println("Campaign Not Created");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		// Logout
		WebElement userIcon = driver.findElement(By.className("user-icon"));
		Actions action = new Actions(driver);
		action.moveToElement(userIcon).perform();
		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		action.moveToElement(logoutBtn).click().perform();

		// Close the browser
		driver.quit();
	}

}
