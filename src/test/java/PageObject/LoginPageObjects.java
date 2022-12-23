package PageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.Scenario;

public class LoginPageObjects {
	
	private static final Logger logger= LogManager.getLogger(LoginPageObjects.class);
	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;
	
//============= Locators for WebElements for SignIn activity =========================================//

	private By myaccount= By.xpath("//a[normalize-space()='My Account']");

//============= Constructor ========================================================================//
	public LoginPageObjects(WebDriver driver,Scenario scn)
	{
		this.driver= driver;
		this.scn=scn;
	}
//	
	
	
////============ 1. Method to validate MyAccount =================================================//
   public void MyAccountValidation()
   {
   	WebElement Account =driver.findElement(myaccount);
   	Assert.assertEquals(true,Account.isDisplayed());
   	logger.info("Validate the MyAccount");
   	scn.log("Validate the MyAccount");
   }
    
//============ 2. Method to click on MyAccount =================================================//
   public void clickOnmyaccount()
   {
   	
   	WebElement Account =driver.findElement(myaccount);
   	
    wait= new WebDriverWait(driver,20);
	wait.until(ExpectedConditions.elementToBeClickable(Account));
   	Account.click();
 	logger.info("Click on the MyAccount Link");
    	scn.log("Click on the MyAccount Link");
   }
   
//---------=====3. Method to validate LoginIn page title =============================================//
 public void validateMyAccount(String MyAccountTitle)
 {
 	wait= new WebDriverWait(driver,20);
 	boolean xyz = wait.until(ExpectedConditions.titleIs(MyAccountTitle));
      Assert.assertEquals(true, xyz);
  	logger.info("Validate title of MyAccount page, so title is: "+ MyAccountTitle);
  	scn.log("Validate title of MyAccount page, so title is: "+ MyAccountTitle);   }
    
    
	//============ 1. Method to validate SignIn Button =====
    
	

}
