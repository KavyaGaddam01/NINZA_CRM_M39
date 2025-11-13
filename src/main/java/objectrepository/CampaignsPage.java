package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	
	WebDriver driver;

	public CampaignsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Create Campaign']") private WebElement addCreateCampaignBtn;

	public WebElement getAddCreateCampaignBtn() {
		return addCreateCampaignBtn;
	}

}
