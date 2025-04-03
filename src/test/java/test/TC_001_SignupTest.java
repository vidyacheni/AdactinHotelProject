package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC_001_SignupTest extends ProjectSpecificationMethods{

	@BeforeTest
	public void setup() {
		
		sheetname="SignUp";
		testname="SignUp Test";
		testdescription="Testing the sign up functionality with valid and invalid details";
		testCategory="Smoke Testing";
		testAuthor="Leema Josephine";
	}
	
	@Test(dataProvider = "readData")
	public void sigupTest(String firstname, String lastName, String emaiId, String password, String testType, String ecpectedMessage) {
		
		
		HomePage obj1 = new HomePage(driver);
		obj1.clickSignup()
		.enterFirstName(firstname)
		.enterLastName(lastName)
		.enterEmailId(emaiId)
		.enterpassword(password)
		.clickSubmitbtn()
		.validateSignUp(testType, ecpectedMessage);
	}
}
