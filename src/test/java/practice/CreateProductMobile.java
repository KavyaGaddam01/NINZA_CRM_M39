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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericutilities.JavaUtility;
import objectrepository.AddProductPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.ProductsPage;

public class CreateProductMobile {

	public static void main(String[] args) throws IOException {

		// Read data from properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\QSP\\Documents\\CommondataM39.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("URL");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");

		// read test script data from excel
		FileInputStream fis1 = new FileInputStream("C:\\Users\\QSP\\Documents\\NINZA_CRM_M39.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String PRODUCT_NAME = wb.getSheet("Products").getRow(1).getCell(2).getStringCellValue();
		String CATEGORY_DD_VALUE = wb.getSheet("Products").getRow(1).getCell(3).getStringCellValue();
		String QUANTITY = wb.getSheet("Products").getRow(1).getCell(4).getStringCellValue();
		String PRICE = wb.getSheet("Products").getRow(1).getCell(5).getStringCellValue();
		String VENDOR_ID = wb.getSheet("Products").getRow(1).getCell(6).getStringCellValue();
		wb.close();
		
		JavaUtility jLib=new JavaUtility();

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
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(URL, USERNAME, PASSWORD);

		// add product mobile
		HomePage  homePage=new HomePage(driver);
		homePage.getProductsLink().click();
		ProductsPage productsPage=new ProductsPage(driver);
		productsPage.getAddProductBtn().click();
		AddProductPage addProductPage=new AddProductPage(driver);
		addProductPage.getProductNameTF().sendKeys(PRODUCT_NAME+jLib.generateRandomNumber());
//		driver.findElement(By.name("productName")).sendKeys(PRODUCT_NAME);	
		WebElement productCategoryDD = addProductPage.getProductCategoryDD();
		Select obj = new Select(productCategoryDD);
		obj.selectByValue(CATEGORY_DD_VALUE); // Electronics
		WebElement quantityTF = addProductPage.getQuantityTF();
		quantityTF.clear();
		quantityTF.sendKeys(QUANTITY);
		WebElement priceTF = addProductPage.getPriceTF();
		priceTF.clear();
		priceTF.sendKeys(PRICE);
		WebElement vendorIdDD = addProductPage.getVendorIdDD();
		Select obj1 = new Select(vendorIdDD);
		obj1.selectByValue(VENDOR_ID); // VID_447
		addProductPage.getAddBtn().click();

		// verification
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
