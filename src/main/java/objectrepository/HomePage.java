package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.WebDriverUtility;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;
	@FindBy(linkText = "Products")
	private WebElement productsLink;
	@FindBy(className = "user-icon")
	private WebElement userIcon;
	@FindBy(xpath = "//div[text()='Logout ']")
	private WebElement logoutBtn;
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement toastMsg;
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement closeToastMsgBtn;

	public WebElement getToastMsg() {
		return toastMsg;
	}

	public WebElement getCloseToastMsgBtn() {
		return closeToastMsgBtn;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}

	public void logout() {
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.mouseHoverOnWebElement(driver, userIcon);
		logoutBtn.click();
	}
}
