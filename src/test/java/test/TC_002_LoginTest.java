package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import base.ProjectSpecificationMethods;
import pages.LoginPage;
import utils.UserData;
import utils.UtilityClass;

import java.io.IOException;

public class TC_002_LoginTest extends ProjectSpecificationMethods {

    @BeforeClass
    public void testDetails() {
        testname = "Login Test";
        testdescription = "Verify login functionality using registered user";
        testCategory = "Functional";
        testAuthor = "Vidya Cheni";

        super.testDetails();
    }

    @Test
    public void verifyLogin() throws IOException {
        try {
            LoginPage loginPage = new LoginPage(driver);

            // Fetch credentials from UserData
            String email = UserData.getEmail();
            String password = UserData.getPassword();

            test.info("Logging in with email: " + email);

            // Perform login
            loginPage.login(email, password);
            test.pass("Login form submitted.");

            // Validate successful login by checking some element on Contact List Page
            boolean isAtContactList = driver.getPageSource().contains("Contact List");
            Assert.assertTrue(isAtContactList, "Login failed or Contact List page not loaded.");
            test.pass("Login successful, Contact List page displayed.");

        } catch (Exception e) {
            String path = UtilityClass.screenShot("LoginTestFailure"); 
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
