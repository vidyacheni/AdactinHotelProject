package test;

import org.openqa.selenium.WebElement;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.ContactDetailsPage;
import pages.LoginPage;
import utils.UserData;



public class TC_006_InvalidBirthdateFormatTest extends ProjectSpecificationMethods {
	
	@BeforeClass
    public void testDetails() {
        testname = "Verify Invalid Birth Date format";
        testdescription = "Verifying that invalid birth date format is not allowed .";
        testCategory = "Functional";
        testAuthor = "Vidya Cheni";
        super.testDetails();
    }

   

    @Test(description = "Verify the application restricts adding birthdates with invalid date formats")
    public void testInvalidBirthdateFormat() {
    	
    	LoginPage loginPage = new LoginPage(driver);
        loginPage.login(UserData.getEmail(), UserData.getPassword());
        test.info("Logged in successfully.");

        // Navigate to Add Contact
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage(driver);
        contactDetailsPage.clickAddContact();
        test.info("Clicked Add Contact button.");
       
    	    	
    	
        String firstName = "Test";
        String lastName = "User";
        String invalidBirthdate = "31-02-2024"; // Example of invalid format / invalid date

        // Log information before starting the test
        test.info("Starting the test for invalid birthdate format.");

        // Wait for the first name field to be visible after logging in
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));

        // Navigate to the Contact Details page and fill out the form
        test.info("Navigating to the Contact Details page and filling out the form.");

        contactDetailsPage.submitContact();  // Ensure that this step is correct

        test.info("Entering first name: " + firstName);
        contactDetailsPage.enterFirstName(firstName);

        test.info("Entering last name: " + lastName);
        contactDetailsPage.enterLastName(lastName);

        test.info("Entering invalid birthdate: " + invalidBirthdate);
        contactDetailsPage.enterBirthdate(invalidBirthdate);

        // Submit the contact form
        test.info("Submitting the contact form.");
        contactDetailsPage.submitContact();

        // Wait for the error message to appear (Explicit Wait for error message visibility)
        test.info("Waiting for the error message for invalid birthdate format.");
        boolean isErrorDisplayed = contactDetailsPage.isBirthdateFormatErrorDisplayed();

        // Assert that error is displayed
        if (isErrorDisplayed) {
            test.pass("Error message for invalid birthdate format was displayed.");
        } else {
            test.fail("Error message for invalid birthdate format was not displayed.");
            Assert.fail("Error message for invalid birthdate format was not displayed.");
        }
    }
}
