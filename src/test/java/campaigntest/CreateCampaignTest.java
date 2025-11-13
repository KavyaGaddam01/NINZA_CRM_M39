package campaigntest;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.ExcelFileUtility;
import genericutilities.JavaUtility;
import genericutilities.PropertyFileUtility;
import genericutilities.WebDriverUtility;
import objectrepository.CampaignsPage;
import objectrepository.CreateCampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;

@Listeners(genericutilities.ListenerImplementation.class)
public class CreateCampaignTest extends BaseClass{

	@Test(groups = {"smoke","regression"})
	public void createCampaignWithMandatoryFieldsTest() throws IOException {
		
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns", 1, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 1, 3);

		// Create Campaign
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

		// Verification
		HomePage homePage = new HomePage(driver);
		WebElement toastMsg = homePage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		homePage.getCloseToastMsgBtn().click();
		assertTrue(msg.contains("Successfully Added"));
		
	}

	@Test(groups = "regression")
	public void createCampaignWithStatusTest() throws EncryptedDocumentException, IOException {

		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns", 4, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 4, 3);
		String STATUS = eLib.readDataFromExcelFile("Campaigns", 4, 4);

		// Create Campaign with mandatory fields
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(CAMPAIGN_NAME);
		driver.findElement(By.name("campaignStatus")).sendKeys(STATUS);
		WebElement targetSize = driver.findElement(By.name("targetSize"));
		targetSize.clear();
		targetSize.sendKeys(TARGET_SIZE);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Verify the toast msg
		HomePage homepage = new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		homepage.getCloseToastMsgBtn().click();
		assertTrue(msg.contains("Successfully Added"));

	}

	@Test(groups = "regression")
	public void createCampaignWithExpectedCloseDateTest() throws IOException {

		// Read test script data from excel file
		ExcelFileUtility eLib = new ExcelFileUtility();
		String CAMPAIGN_NAME = eLib.readDataFromExcelFile("Campaigns", 7, 2);
		String TARGET_SIZE = eLib.readDataFromExcelFile("Campaigns", 7, 3);

		// Create Campaign with mandatory fields
		CampaignsPage campaignsPage = new CampaignsPage(driver);
		campaignsPage.getAddCreateCampaignBtn().click();
		CreateCampaignPage createCampaignPage = new CreateCampaignPage(driver);
		createCampaignPage.getCampaignNameTF().sendKeys(CAMPAIGN_NAME);
		createCampaignPage.getExpectedCloseDateTF().sendKeys(jLib.getRequiredDate(50));
		createCampaignPage.getTargetSizeTF().clear();
		createCampaignPage.getTargetSizeTF().sendKeys(TARGET_SIZE);
		createCampaignPage.getCreateCampaignBtn().click();

		// Verify the toast msg
		HomePage homepage = new HomePage(driver);
		WebElement toastMsg = homepage.getToastMsg();
		wLib.waitUntilElementToBeVisible(driver, toastMsg);
		String msg = toastMsg.getText();
		homepage.getCloseToastMsgBtn().click();
		assertTrue(msg.contains("Successfully Added"));

	}
}
