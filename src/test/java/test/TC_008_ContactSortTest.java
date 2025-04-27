package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.ContactDetailsPage;
import pages.LoginPage;
import utils.UserData;

import java.util.List;

public class TC_008_ContactSortTest extends ProjectSpecificationMethods {

    private LoginPage loginPage;
    private ContactDetailsPage contactDetailsPage;

    @BeforeClass
    public void testDetails() {
        testname = "Verify Contacts are sorted";
        testdescription = "Verifying that Contacts are sorted by Last Name .";
        testCategory = "Functional";
        testAuthor = "Vidya Cheni";
        super.testDetails();
    }
    

    @Test(description = "Verify contacts are displayed in alphabetical order by last name")
    public void verifyContactsAlphabeticalOrder() {
    	
    	LoginPage loginPage = new LoginPage(driver);
        loginPage.login(UserData.getEmail(), UserData.getPassword());
        test.info("Logged in successfully.");

        // Navigate to Add Contact
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage(driver);
        //contactDetailsPage.clickAddContact();
       // test.info("Clicked Add Contact button.");
    	    	
    	
        test.info("Starting test to verify alphabetical order of contacts by last name.");
        
        // Adding contacts with different last names
        //contactDetailsPage.clickAddContact();
       // contactDetailsPage.enterFirstName("Alex");
       // contactDetailsPage.enterLastName("B");
       // contactDetailsPage.submitContact();
       // test.info("Added Alex B.");
        
       // contactDetailsPage.clickAddContact();
       // contactDetailsPage.enterFirstName("Paul");
        //contactDetailsPage.enterLastName("D");
        //contactDetailsPage.submitContact();
        //test.info("Added Paul D.");
        
        //contactDetailsPage.clickAddContact();
        //contactDetailsPage.enterFirstName("Tom");
        //contactDetailsPage.enterLastName("K");
       // contactDetailsPage.submitContact();
       // test.info("Added Tom K.");

        // Wait for the contacts list to refresh and be visible
        //contactDetailsPage.waitForContactsListToUpdate();

        // Capture the list of contacts by full name (First + Last)
        List<String> contactsList = contactDetailsPage.getContactsList();
        
        // Check if the list is in alphabetical order by last name
        for (int i = 1; i < contactsList.size(); i++) {
            String previousContact = contactsList.get(i - 1);
            String currentContact = contactsList.get(i);
            
            // Split the full name into First Name and Last Name
            String previousLastName = previousContact.split(" ")[1]; // Extract last name
            String currentLastName = currentContact.split(" ")[1];   // Extract last name
            
            // Assert that the contacts are in alphabetical order by last name
            Assert.assertTrue(previousLastName.compareTo(currentLastName) <= 0,
                    "Contacts are not in alphabetical order: " + previousContact + " comes after " + currentContact);
        }

        test.pass("Contacts are correctly displayed in alphabetical order by last name.");
    }
}
