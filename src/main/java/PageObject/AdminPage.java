package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {

	WebDriver ldriver;

	// Parameterized constructor
	public AdminPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	// driver.findElement(By.xpath("//input[@id='Email']"));

	@FindBy(xpath = "//input[@id='Email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='Password']")
	WebElement txtPassword;

	@FindBy(xpath = "//button[text()='Log in']")
	WebElement btnLogin;

	// User define method to perform operation on web element

	public void setUserName(String uName) {
	  txtEmail.clear();
	  
      txtEmail.sendKeys(uName);
	}

	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}

	public void clickOnLoginButton() {
		btnLogin.click();
	}

}
