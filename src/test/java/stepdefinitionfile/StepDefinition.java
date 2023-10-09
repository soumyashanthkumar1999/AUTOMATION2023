package stepdefinitionfile;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import PageObject.AddNewCustomerPage;
import PageObject.AdminPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import utilities.ReadConfig;

public class StepDefinition extends Base {
	
	//@Before will execute before every scenario
		@Before
		public void setUp() throws Exception {
			
			System.out.println("SetUp method execution");
			readConfig=new ReadConfig();//create object of ReadConfig Java class
			
			String browser=readConfig.getBrowser();//value =firefox
			
			if(browser.equalsIgnoreCase("Chrome")) {
				driver=new ChromeDriver();
				Thread.sleep(1000);
				driver.manage().window().maximize();
				Thread.sleep(1000);

			}else if(browser.equalsIgnoreCase("firefox")) {
				driver=new FirefoxDriver();
				Thread.sleep(1000);

				driver.manage().window().maximize();
				Thread.sleep(1000);


			}else if(browser.equalsIgnoreCase("IE")) {
				driver=new InternetExplorerDriver();
				Thread.sleep(1000);

				driver.manage().window().maximize();
				Thread.sleep(1000);

			}
			
		}
		
		@Given("User Lanch Chrome Browser")
		public void user_lanch_chrome_browser() throws Exception {
		    
			//driver=new ChromeDriver();
			
			//create object of AdminPage java class
			
			ad=new AdminPage(driver);
			Thread.sleep(2000);
			
			
		}

		@When("User open url {string}")
		public void user_open_url(String url) throws Exception {
		   
			driver.get(url);
			Thread.sleep(2000);

		}

		@When("User enter Email as {string} and password as {string}")
		public void user_enter_email_as_and_password_as(String email, String password) throws Exception {
		   
			ad.setUserName(email);
			Thread.sleep(2000);

			ad.setPassword(password);
			Thread.sleep(2000);

		}

		@When("User click on Login button")
		public void user_click_on_login_button() throws Exception {
	        ad.clickOnLoginButton();
			Thread.sleep(2000);

		}
		
		@Then("User verify page title should be {string}")
		public void user_verify_page_title_should_be(String title) throws Exception {
		  
			Assert.assertEquals(driver.getTitle(),title);
			Thread.sleep(2000);
			
		}

		@Then("close browser")
		public void close_browser() throws Exception {
	         driver.close();
	 		Thread.sleep(2000);

			
		}
		
		//add new customer
		
			@Then("User can view Dashboard")
			public void user_can_view_dashboard() throws InterruptedException {
				addCust=new AddNewCustomerPage(driver);
				   Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
				    Thread.sleep(2000);
			}

			@When("user click on customers menu")
			public void user_click_on_customers_menu() throws Exception {
				 addCust.clickOnCustomesMenu();
				    Thread.sleep(2000);
			}

			@When("User click on customers menu item")
			public void user_click_on_customers_menu_item() throws InterruptedException {
				 addCust.clickOnCustomesMenuItem();
				    Thread.sleep(2000);
			}

			@When("User click on new add button")
			public void user_click_on_new_add_button() throws Exception {
				addCust.clickOnAddNew();
			    Thread.sleep(2000);
			}

			@Then("User can view add new customer page")
			public void user_can_view_add_new_customer_page() throws Exception {
				 Thread.sleep(2000);
			       Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
			}

			@When("user enter customer info as {string} and {string} and {string} and {string} and {string} and {string} and {string}")
			public void user_enter_customer_info_as_and_and_and_and_and_and(String newEmail, String NewPassword, String firstName, String lastName, String gender, String compName, String adminComment) throws Exception {
				 addCust.SetEmail(newEmail);
				  addCust.SetPassword(NewPassword);
				  addCust.SetFirstName(firstName);
				  addCust.SetLastName(lastName);
				  addCust.SetGender(gender);
				//  addCust.enterDob("6/13/1988");
				//  addCust.enterManagerOfVendor("Vendor 1");
				  addCust.SetCompanyName(compName);
				  addCust.SetAdminContent(adminComment);
				    Thread.sleep(2000);
			}

			@When("user click on save button")
			public void user_click_on_save_button() {
				   addCust.clickOnSave();
			}

			@Then("User can view confirmation msg {string}")
			public void user_can_view_confirmation_msg(String string) throws Exception {
				 Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));
				    Thread.sleep(2000);
			}
			

		
		@After
		public void teadDown(Scenario sc) throws Exception {
			System.out.println("Tear down method execute after each test case scenario");
			
			if(sc.isFailed()==true) {
				
			String filewithpath="C:\\Users\\User\\eclipse-workspace\\CucumberMavenProject\\screenshot\\failedScreenshot.png";
			
			//convert WebDriver object into TakeScreenshot

			TakesScreenshot scrshot=((TakesScreenshot)driver);
			
			//call getScremshotAs() to store image file
			
			File scrFile=scrshot.getScreenshotAs(OutputType.FILE);
			
			
			File destFile=new File(filewithpath);
			
			FileUtils.copyFile(scrFile, destFile);
			}
			
			Thread.sleep(2000);
			driver.quit();
		}
}
