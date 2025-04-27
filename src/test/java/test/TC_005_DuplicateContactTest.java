package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.LoginPage;
import utils.UserData;
import pages.ContactDetailsPage;

public class TC_005_DuplicateContactTest extends ProjectSpecificationMethods {

	@BeforeClass
    public void testDetails() {
        testname = "Add Duplicate Contact";
        testdescription = "Verify that a duplicate contact can be added.";
        testCategory = "Functional";
        testAuthor = "Vidya Cheni";
        super.testDetails();
    }

    

    @Test
    public void testDuplicateContactAddition() {
    	
    	 // Login first
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(UserData.getEmail(), UserData.getPassword());
        test.info("Logged in successfully.");

        // Navigate to Add Contact
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage(driver);
        contactDetailsPage.clickAddContact();
        test.info("Clicked Add Contact button.");
       
        
        String firstName = "John";
        String lastName = "Doe";

        // Enter the contact details
        test.info("Entering first name: " + firstName);
        contactDetailsPage.enterFirstName(firstName);
        
        test.info("Entering last name: " + lastName);
        contactDetailsPage.enterLastName(lastName);
        
        // Submit the contact form
        test.info("Submitting the contact form.");
        contactDetailsPage.submitContact();
        
        // Check if contact is added
        test.info("Verifying if the contact was added to the contact list.");
        boolean isContactAdded = contactDetailsPage.isContactPresent(firstName, lastName);

        // Verify whether the duplicate contact was added
        if (isContactAdded) {
            test.fail("Duplicate contact was incorrectly added to the contact list.");
            Assert.fail("Duplicate contact was incorrectly added to the contact list.");  // Fail the test
        } else {
            test.pass("Duplicate contact was correctly restricted and not added to the list.");
        }
    }
}