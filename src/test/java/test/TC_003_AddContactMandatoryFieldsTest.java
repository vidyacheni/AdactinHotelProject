package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import base.ProjectSpecificationMethods;
import pages.LoginPage;
import pages.ContactDetailsPage; // Assuming you have/will create this
import utils.UserData;
import utils.UtilityClass;

import java.io.IOException;



public class TC_003_AddContactMandatoryFieldsTest extends ProjectSpecificationMethods {

    @BeforeClass
    public void testDetails() {
        testname = "Add Contact Test - Mandatory Fields Only";
        testdescription = "Verify adding a contact with only mandatory fields (First Name and Last Name)";
        testCategory = "Functional";
        testAuthor = "Vidya Cheni";

        super.testDetails();
    }

    @Test
    public void addContactWithMandatoryFields() throws IOException {
        try {
            // Step 1: Login
            LoginPage loginPage = new LoginPage(driver);
            String email = UserData.getEmail();
            String password = UserData.getPassword();
            loginPage.login(email, password);
            test.pass("Logged in successfully with valid credentials.");

            // Step 2: Add contact with only mandatory fields
            ContactDetailsPage contactPage = new ContactDetailsPage(driver);

            String firstName = "TestFirst";
            String lastName = "TestLast";

            contactPage.clickAddContact(); // Click on Add Contact
            contactPage.enterFirstName(firstName);
            contactPage.enterLastName(lastName);
            contactPage.submitContact(); // Submit the form
            test.info("Entered mandatory fields and submitted contact form.");

            // Step 3: Verify contact added
            boolean isContactAdded = contactPage.isContactPresent(firstName, lastName);
            Assert.assertTrue(isContactAdded, "Contact was not added successfully.");
            test.pass("Contact added successfully with mandatory fields.");

        } catch (Exception e) {
            String path = UtilityClass.screenShot("AddContactMandatoryFieldsFailure"); 
            test.fail("Exception occurred: " + e.getMessage());
            test.fail("Screenshot:").addScreenCaptureFromPath(path);
            Assert.fail("Test Failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
