package StepDef;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Core.WebDriverFactory;
import PageObject.CommonPageObjects;
import PageObject.HeaderSectionObjects;
import PageObject.LoginPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefination {
	private static final Logger logger=LogManager.getLogger(StepDefination.class);
	
	//private static final Logger logger = LogManager.getLogger(StepDefination.class);
	WebDriver driver;
	Scenario scn; //variable scn
	String base_url = "https://practice.automationtesting.in/shop/";

//============== Declare object reference of pageObjects classes =============================//
	
	CommonPageObjects cmnPageObject;
	HeaderSectionObjects headerObject;
	LoginPageObjects loginPageObject ;
	
//====================== Before Hook =========================================================//	
	@Before
	public void setUp(Scenario scn)//Parameterize Scn
	{
		this.scn=scn;

		//Get the desired browser to be invoked
		String browserName= WebDriverFactory.getBrowserName();
		driver=WebDriverFactory.getWebDriverForBrowser(browserName); 
		scn.log("Browser get invoked");// this is occure in report not on console

		//Initialize object of page objects classes 
		cmnPageObject = new CommonPageObjects(driver, scn);
		headerObject = new HeaderSectionObjects(driver, scn);
		loginPageObject =new LoginPageObjects(driver, scn);
	}

//====================== After Hook =========================================================//
	
	@After(order=2)
	//Capture screenshot if test case get failed
	public void captureScreenshot(Scenario scn)
	{
		if(scn.isFailed())
		{
			TakesScreenshot srnshot= ((TakesScreenshot)driver);
			byte [] data =srnshot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Name of failed step is: "+ scn.getName());
			scn.log("Attach a screenshot as step get failed");
		}
		else
		{
			scn.log("Test case get passed, no screenshot is captured");
		}
	}
	
	@After(order=1)
	//Quit the browser
	public void tearDown(Scenario scn)
	{
		WebDriverFactory.quitTheBrowser();
		scn.log("Browser is quit");
	}
	
//====================== Background ================================================//
	@Given("User navigate to URL and open the landing page")
	public void user_navigate_to_url_and_open_the_landing_page() {
		WebDriverFactory.navigateToURL(base_url);
		//scn.log("Launched base url : "+ base_url);
		
	    }

//===================== Scenarios =================================================//

	@When("User is on landing page")
	public void user_is_on_landing_page() {
		logger.info("User is on landing page after navigating to base URL");
	    scn.log("User is on landing page after navigating to base URL");
	}
	@Then("Validate current URL of landing page with expected URL")
	public void validate_current_url_of_landing_page_with_expected_url() {
		 cmnPageObject.validatePageURL();
		 scn.log("Validate current URL is: "+ driver.getCurrentUrl());
	}
	
	@Then("Validate title of landing page with expected title as {string}")
	public void validate_title_of_landing_page_with_expected_title_as(String titleOfPage) {
		cmnPageObject.validatePageTitle(titleOfPage);
		scn.log("Validate page title is: "+ driver.getTitle());
	}
		
	@Then("User see the logo of application")
	public void user_see_the_logo_of_application() {
		cmnPageObject.displayLogo();
		scn.log("Display the application logo on landing page");
	}
	
	@When("fetch the height of logo")
	public void fetch_the_height_of_logo() {
		cmnPageObject.fetchLogoHeight();
	}

	@Then("Height of logo should be {string}")
	public void height_of_logo_should_be(String height) {
		cmnPageObject.logoHeightValid(height);
	}
		
	@When("fetch the width of logo")
	public void fetch_the_width_of_logo() {
		cmnPageObject.fetchLogoWidth(); 
	}

	@Then("Width of logo should be {string}")
	public void width_of_logo_should_be(String width) {
		cmnPageObject.logoWidthValid(width);
	}
	
//	@Given("User see Shop Link")
//	public void user_see_shop_link() {
//		headerObject.shopPage();
//		scn.log("Display the Shop link");
//	}
//
//	@When("User click on shop")
//	public void user_click_on_shop_page() {
//		headerObject.clickOnshopLink();
//	}
//	@Then("User is on shop page which have expected page title as {string}")
//	public void user_is_on_shop_page_which_have_expected_page_title_as(String ShopPageTitle) {
//		headerObject.shopValidation(ShopPageTitle);
//	}

//	@Given("User click on shop link")
//	public void user_click_on_shop_link() {
//		HeaderSectionObjects. ();
//		headerObject.clickOnshopLink();
//	}

//	@When("navigate to shop account page")
//	public void navigate_to_shop_account_page() {
//		headerObject.shopPage();
//	}
	
		
	@When("User see the product category")
	public void user_see_the_product_category() {
		cmnPageObject.setProdCategory();
	}

	@Then("Validate product category as per expected product category listed below")
	public void validate_product_category_as_per_expected_product_category_listed_below(List<String> list) {
		cmnPageObject.validateProdCategory(list); 
		scn.log("Validate the product category with expected datatable");
	    
	}
	@Then("Size of product category should be {int}")
	public void size_of_product_category_should_be(Integer prodCatCount) {
		cmnPageObject.sizeOfProdCategory(prodCatCount);
	}
	
	@Given("User click on MyAccount from home page")
	public void user_click_on_MyAccount_from_home_page() {
		WebElement  myaccount= driver.findElement(By.xpath("//a[normalize-space()='My Account']"));
		myaccount.click();
	}
	
@When("User redirected to login page of the application where title us {string}")
public void user_redirected_to_login_page_of_the_application_where_title_us(String loginPageTitle) {
String titleOfLoginpage = driver.getTitle();
	Assert.assertEquals(loginPageTitle, titleOfLoginpage);
	}
	
	@When("User enters {string} and {string} and click on Login button")
	public void user_enters_and_and_click_on_login_button(String username, String password) {	
	WebElement emailIdInputFieldElement = driver.findElement(By.xpath("//input[@id='username']"));
		emailIdInputFieldElement.sendKeys(username);
		WebElement passwordInputFieldElement = driver.findElement(By.xpath("//input[@id='password']"));
		passwordInputFieldElement.sendKeys(password);
		WebElement loginInButtonElement = driver.findElement(By.xpath("//input[@name='login']"));
		loginInButtonElement.click();
	}
	
	@Then("User successfully login and landing to next page")
	public void user_successfully_login_and_landing_to_next_page() {
	
}
//	@Then("User successfully redirected to {string} page with user name displayed of the {string} and {string}")
//	public void user_successfully_redirected_to_page_with_user_name_displayed_of_the_and(String loggedInPageTitle, String firstname, String lastname) {
//		Assert.assertEquals(loggedInPageTitle, driver.getTitle());
//
//		WebElement userDetailsElement = driver.findElement(By.xpath("//a[@title='View my customer account']/span"));
//		String[] username = userDetailsElement.getText().split(" ");
//		String userFirstName = username[0];
//		String userLastName = username[1];
//		
//		Assert.assertEquals(firstname+ " " +lastname, userFirstName+ " " +userLastName);
//	}
//	
	@Then("User is unable to login with an error message {string}")
	public void user_is_unable_to_login_with_an_error_message(String authenticationFailedMessage) {
	   
//		WebElement failedLoginMsgElement = driver.findElement(By.xpath("//div[@id='body']//li ='Authentication failed.']"));
//		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='center_column']//li[text()='Authentication failed.']")));
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Assert.assertEquals(authenticationFailedMessage, failedLoginMsgElement.getText());

	}
}


