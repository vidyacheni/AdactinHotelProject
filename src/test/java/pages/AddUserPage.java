package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
}
