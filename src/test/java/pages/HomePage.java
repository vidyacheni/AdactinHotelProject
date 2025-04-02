package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ProjectSpecificationMethods;

public class HomePage extends ProjectSpecificationMethods  {

	
	@FindBy(id="signup")
	WebElement signup;
	
	public HomePage (WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public AddUserPage clickSignup() {
		
			 signup.click();
			 return new AddUserPage(driver);
		
	}
	
}
