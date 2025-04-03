package pages;

import static org.testng.Assert.expectThrows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.ProjectSpecificationMethods;

public class AddUserPage extends ProjectSpecificationMethods{

	@FindBy(id="firstName")
	WebElement firstname;
	
	@FindBy(id="lastName")
	WebElement lastname;
	
	@FindBy(id="email")
	WebElement emailId;
	
	@FindBy(id="password")
	WebElement pass;
	
	@FindBy(id="submit")
	WebElement submitBtn;
	
	@FindBy(xpath="//span[text()='User validation failed: email: Email is invalid']")
	WebElement errorMsg;
	
	@FindBy(xpath="//h1[text()='Contact List']")
	WebElement contactList;
	
	public AddUserPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public AddUserPage enterFirstName(String fn) {
		
		firstname.sendKeys(fn);
		return this;
	}
	
	public AddUserPage enterLastName(String ln) {
		
		lastname.sendKeys(ln);
		return this;
	}
	
	public AddUserPage enterEmailId(String id) {
		
		emailId.sendKeys(id);
		return this;
	}
	
	public AddUserPage enterpassword(String password) {
		
		pass.sendKeys(password);
		return this;
	}
	
	public AddUserPage clickSubmitbtn() {
		
		submitBtn.click();
		return this;
	}
	
	public void validateSignUp(String testType, String ExpectedMessage) {
		
		if(testType.equalsIgnoreCase("ValidCredentials")) {
			
			String actualMessage = contactList.getText();
			Assert.assertEquals(actualMessage, ExpectedMessage);
		} 
		else if(testType.equalsIgnoreCase("InvalidEmail")) {
			
			String actualMessage = errorMsg.getText();
			Assert.assertEquals(actualMessage, ExpectedMessage);
		}
	}
}
