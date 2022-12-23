package PageObject;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Core.WebDriverFactory;
import io.cucumber.java.Scenario;

public class HeaderSectionObjects {
	
	private static final Logger logger= LogManager.getLogger(HeaderSectionObjects.class);
	WebDriver driver;
	WebDriverWait wait;
	//JavascriptExecutor js;
	Scenario scn;
	
//============= Locators for Header section WebElements=====================================//

	private By shopLink= By.xpath("//a[normalize-space()='Shop']");
	private By shopName= By.xpath("(//title[contains(text(),'Products – Automation Practice Site')]");

//============= Expected Results ==========================================================//

	String ShopPageTitle= "Product – Automation Practice Site";
	
	
//============= Constructor ===============================================================//
	public HeaderSectionObjects(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
	
////============ 1. Method to set shop link =================================================//
   public void setshopLink()
   {
   	WebElement shopElement =driver.findElement(shopLink);
		Assert.assertEquals(true, shopElement.isDisplayed());
   	logger.info("Validate the shop link");
   }
    
//============ 2. Method to click the shop link ===========================================//
    public void clickOnshopLink()
    {
    	WebElement shopElement =driver.findElement(shopLink);
    	shopElement.click();
    	logger.info("Click the shop link");
    	scn.log("Click the shop link");
    }
    
//============ 3. Method to validate shop account page ===================================//
    public void shopPage()
    {
    	WebDriverFactory.switchToNewWindow();
    	logger.info("Switch to Shop Account window");
    	wait= new WebDriverWait(driver, 20);
    	boolean p =wait.until(ExpectedConditions.titleIs(ShopPageTitle));
    	Assert.assertEquals(true, p);
    	logger.info("Validate shop page with its title, title is: "+ ShopPageTitle);
    	scn.log("navigate to shop page, page title is: "+ ShopPageTitle);
    }

//============ 4. Method to validate shop account name ==================================//
    public void shopValidation(String AcName)
    {
   	WebElement shopElement =driver.findElement(shopName);
   	Assert.assertEquals(AcName, shopElement.getText());
   	logger.info("Validate shop account name, account name is: "+shopElement.getText());
   	scn.log("Validate shop account name, account name is: "+shopElement.getText());
    }

	
}
//=======================================================================================================================//

        


