package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.ContactDetailsPage;
import pages.LoginPage;
import utils.UserData;
import utils.UtilityClass;

/**
 * Test Class: Add contact with mandatory + some optional fields (like Email, City, Country).
 */
public class TC_004_MandatoryAndSomeOptionalTest extends ProjectSpecificationMethods {

    @BeforeClass
    public void testDetails() {
        testname = "Add Contact With Some Optional Fields Test";
        testdescription = "Verify that a contact can be added with mandatory fields and some optional fields filled.";
        testCategory = "Functional";
        testAuthor = "Vidya Cheni";
        super.testDetails();
    }

    @Test
    public void addContactWithMandatoryAndSomeOptionalFields() throws IOException {
        try {
            // Login first
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(UserData.getEmail(), UserData.getPassword());
            test.info("Logged in successfully.");

            // Navigate to Add Contact
            ContactDetailsPage contactDetailsPage = new ContactDetailsPage(driver);
            contactDetailsPage.clickAddContact();
            test.info("Clicked Add Contact button.");

         // Fill mandatory fields
            String firstName = "TestPartial";
            String lastName = "Fields";
            contactDetailsPage.enterFirstName(firstName);
            contactDetailsPage.enterLastName(lastName);

            // Fill some optional fields
            contactDetailsPage.enterEmail("partialfields@example.com");
            contactDetailsPage.enterCity("New York");
            contactDetailsPage.enterCountry("United States");

            // Other optional fields left empty (phone, address, postal code, etc.)

            test.info("Entered First Name, Last Name, Email, City, and Country.");

            // Submit the form
            contactDetailsPage.submitContact();
            test.info("Clicked Submit button.");

            // Validate that contact is added successfully
            Assert.assertTrue(contactDetailsPage.isContactPresent(firstName, lastName),
                    "Contact not found after submission.");
            test.pass("Contact added successfully with mandatory and some optional fields.");

        } catch (Exception e) {
            String path = UtilityClass.screenShot("AddContactWithSomeOptionalFieldsFailure");
            test.fail("Exception occurred: " + e.getMessage());
            test.fail("Screenshot:").addScreenCaptureFromPath(path);
            Assert.fail("Test Failed due to exception: " + e.getMessage());
        }
    }
}