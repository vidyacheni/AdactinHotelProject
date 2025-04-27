package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.ContactDetailsPage;
import pages.LoginPage;
import utils.UserData;

public class TC_009_PhoneExtensionTest extends ProjectSpecificationMethods {

    private LoginPage loginPage;
    private ContactDetailsPage contactDetailsPage;

    @BeforeClass
    public void testDetails() {
        testname = "Verify Phone Number with Extension";
        testdescription = "Verify that the phone number is displayed correctly with its extension.";
        testCategory = "Functional";
        testAuthor = "Vidya Cheni";
        super.testDetails();
    }
    
    @Test(description = "Verify phone number with its extension is displayed correctly")
    public void verifyPhoneNumberWithExtension() {
        test.info("Starting test to add a contact and verify phone number with extension.");
        
        // Log in
        loginPage = new LoginPage(driver);
        loginPage.login(UserData.getEmail(), UserData.getPassword());
        test.info("Logged in successfully.");
        
        // Navigate to Add Contact
        contactDetailsPage = new ContactDetailsPage(driver);
        contactDetailsPage.clickAddContact();
        test.info("Clicked Add Contact button.");
        
        // Prepare expected contact details
        String expectedFirstName = "Phone";
        String expectedLastName = "Ext";
        String expectedPhoneNumber = "+919876543210";  // Example phone number with extension
        
        // Enter contact details
        contactDetailsPage.enterFirstName(expectedFirstName);
        contactDetailsPage.enterLastName(expectedLastName);
        contactDetailsPage.enterPhoneNumber(expectedPhoneNumber);
        test.info("Entered First Name, Last Name, and Phone Number.");

        // Submit the contact
        contactDetailsPage.submitContact();
        test.info("Submitted the new contact with phone number and extension.");

        // Wait for the contact details page to update
        contactDetailsPage.waitForContactDetailsPage();

        // Capture the phone number displayed on the contact details page
        String actualPhoneNumber = contactDetailsPage.getPhoneNumberText().trim();

        // Verify the phone number with extension is displayed correctly
        Assert.assertEquals(actualPhoneNumber, expectedPhoneNumber, "Phone number with extension is not displayed correctly.");
        test.pass("Phone number with extension is displayed correctly.");
    }
}
