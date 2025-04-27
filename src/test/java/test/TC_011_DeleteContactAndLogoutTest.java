package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactEditingPage;
import pages.LoginPage;
import utils.UserData;

public class TC_011_DeleteContactAndLogoutTest extends ProjectSpecificationMethods 
{
    private LoginPage loginPage;
    private ContactEditingPage contactEditingPage;

    @BeforeClass
    public void testDetails() 
    {
        testname = "Verify Contact Deletion";
        testdescription = "Test that verifies the ability to delete contact details.";
        testCategory = "Functional";
        testAuthor = "Vidya Cheni";
        super.testDetails();
    }

    @Test(description = "Test deleting a contact by clicking on City and verifying deletion by City name")
    public void deleteContactByCity() 
    {
        test.info("Starting test to verify contact deletion by clicking City and verifying City absence.");

        loginPage = new LoginPage(driver);
        loginPage.login(UserData.getEmail(), UserData.getPassword());
        test.info("Logged in successfully.");

        contactEditingPage = new ContactEditingPage(driver);
        contactEditingPage.waitForContactsListToUpdate();

        contactEditingPage.clickOnCityCell();
       // test.info("Clicked on the City cell of the contact.");

        contactEditingPage.clickDeleteContactButton();
        test.info("Clicked Delete Contact button.");

        Assert.assertTrue(contactEditingPage.isAlertPresent(), "Delete confirmation alert not displayed.");
        contactEditingPage.acceptAlert();
        test.info("Accepted the delete confirmation alert.");

        contactEditingPage.waitForContactsListToUpdate();

        String cityName = "Hyderabad";
        boolean isCityStillPresent = contactEditingPage.isCityPresentInList(cityName);
        Assert.assertFalse(isCityStillPresent, "City still present in contact list. Contact was not deleted.");
        test.pass("Contact deleted successfully, no other contacts affected.");
        
        
     // Check if Logout Button is displayed
        Assert.assertTrue(contactEditingPage.isLogoutButtonDisplayed(), "Logout button not visible on contact list page.");
        test.pass("Logout button is visible.");

        // Click Logout Button
        contactEditingPage.clickLogoutButton();
        test.info("Clicked Logout button.");
        
        try {
            Thread.sleep(5000); // Wait for 5 seconds to ensure redirection (for slow networks or animations)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        // Verify redirection to Login Page
        Assert.assertTrue(contactEditingPage.isLoginPageDisplayed(), "Did not redirect to Login page after logout.");
        test.pass("Successfully redirected to Login page after logout.");

        test.info("Test for deleting contact and logging out completed successfully.");
    }
    }
    

