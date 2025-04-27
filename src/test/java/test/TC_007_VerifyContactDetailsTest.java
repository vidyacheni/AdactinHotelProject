package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.ContactDetailsPage;
import pages.LoginPage;
import utils.UserData;

public class TC_007_VerifyContactDetailsTest extends ProjectSpecificationMethods {

    private LoginPage loginPage;
    private ContactDetailsPage contactDetailsPage;

    
    @Test(description = "Verify contact details are displayed correctly after adding")
    public void verifyContactDetails() 
    
    {
    	loginPage = new LoginPage(driver);
        contactDetailsPage = new ContactDetailsPage(driver);

        test.info("Logging in with user credentials.");
        loginPage.login(UserData.getEmail(), UserData.getPassword());
        
        test.info("Starting test to add and verify contact details.");

        contactDetailsPage.clickAddContact();
        test.info("Clicked on Add Contact.");

        // Prepare expected details
        String expectedFirstName = "First";
        String expectedLastName = "ALast";

        // Concatenate first and last name to form the full name
        String expectedFullName = expectedFirstName + " " + expectedLastName;

        // Enter details
        contactDetailsPage.enterFirstName(expectedFirstName);
        contactDetailsPage.enterLastName(expectedLastName);

        // Submit contact
        contactDetailsPage.submitContact();
        test.info("Submitted the new contact with expected details.");

        //  Wait for the page to update before capturing details
        contactDetailsPage.waitForContactDetailsPage();  // <--- we'll add this method

        // Capture actual full name from the displayed contact
        String actualFullName = contactDetailsPage.getFullNameText().trim();  

        test.info("Captured Full Name: '" + actualFullName + "'");

        // Compare actual full name vs expected full name
        Assert.assertEquals(actualFullName, expectedFullName, "Full Name does not match.");

        test.pass("Contact details verified successfully after adding.");
    }
}