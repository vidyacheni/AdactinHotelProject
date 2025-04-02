package test;

import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC_001_SignupTest extends ProjectSpecificationMethods{

	@Test
	public void signupTest() {
		
		HomePage obj = new HomePage(driver);
		obj.clickSignup()
		.enterFirstName("DummyTest")
		.enterLastName("User")
		.enterEmailId("DummyTestUser91@gmail.com")
		.enterpassword("DummyTest")
		.clickSubmitbtn();
	}
}
