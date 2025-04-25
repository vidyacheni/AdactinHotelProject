package test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.SignupPage;
import utils.UtilityClass;

public class TC_001_SignupTest extends ProjectSpecificationMethods {

    @BeforeClass
    public void testDetails() {
        // Set specific test details for this class
        testname = "Signup Test";
        testdescription = "Verify the signup functionality for a new user";
        testCategory = "Functional"; // You can assign a category here
        testAuthor = "Vidya Cheni"; // You can assign an author here

        // Call the parent method to initialize the test in ExtentReports
        super.testDetails();
    }

    @Test(dataProvider = "signupData")
    public void verifySignupPositiveScenario(String firstName, String lastName, String email, String password, String testType, String expectedMessage) throws IOException {
        // Ensure testType matches the expected value (Positive or Negative)
        testType = testType.trim();
        test.info("Test Type: " + testType);
        
        if (!testType.equalsIgnoreCase("Positive")) {
            test.info("Skipping positive test for: " + firstName + " " + lastName);
            return;
        }

        try {
            SignupPage signupPage = new SignupPage(driver);

            // Visible and clickable checks only for the Positive test type
            Assert.assertTrue(signupPage.isSignupButtonVisible(), "Sign up button is not visible.");
            test.pass("Sign up button is visible.");
                
            Assert.assertTrue(signupPage.isSignupButtonClickable(), "Sign up button is not clickable.");
            test.pass("Sign up button is clickable.");

            // Step 2: Clicking the Sign up button
            signupPage.clickSignupButton();
            test.info("Clicked Signup button.");

            // Step 3: Waiting for Add User page to be displayed
            Assert.assertTrue(signupPage.isAddUserPageDisplayed(), "Add User page did not load successfully.");
            test.pass("Add User page displayed successfully.");

            // Step 4: Filling the signup form
            signupPage.enterFirstName(firstName);
            signupPage.enterLastName(lastName);
            signupPage.enterEmail(email);
            signupPage.enterPassword(password);
            test.info("Entered user details.");

            // Step 5: Submitting the form
            signupPage.clickSubmit();
            test.info("Clicked Submit button.");

            // Test success flow: Ensure navigation to Contact List page
            boolean isAtContactList = signupPage.isContactListPageDisplayed();
            Assert.assertTrue(isAtContactList, "Signup failed, did not navigate to Contact List page.");
            test.pass("Signup successful, navigated to Contact List page.");
        } catch (Exception e) {
            String path = UtilityClass.screenShot("SignupTestFailure"); 
            test.fail("Exception occurred: " + e.getMessage());
            test.fail("Screenshot:").addScreenCaptureFromPath(path);
            Assert.fail("Test Failed due to exception: " + e.getMessage());
        }
    }

    @Test(dataProvider = "signupData")
    public void verifySignupNegativeScenario(String firstName, String lastName, String email, String password, String testType, String expectedMessage) throws IOException {
        // Ensure testType matches the expected value (Positive or Negative)
        testType = testType.trim();
        test.info("Test Type: " + testType);
        
        if (!testType.equalsIgnoreCase("Negative")) {
            test.info("Skipping negative test for: " + firstName + " " + lastName);
            return;
        }

        try {
            SignupPage signupPage = new SignupPage(driver);

            // Visible and clickable checks only for the Negative test type
            Assert.assertTrue(signupPage.isSignupButtonVisible(), "Sign up button is not visible.");
            test.pass("Sign up button is visible.");
                
            Assert.assertTrue(signupPage.isSignupButtonClickable(), "Sign up button is not clickable.");
            test.pass("Sign up button is clickable.");

            // Step 2: Clicking the Sign up button
            signupPage.clickSignupButton();
            test.info("Clicked Signup button.");

            // Step 3: Waiting for Add User page to be displayed
            Assert.assertTrue(signupPage.isAddUserPageDisplayed(), "Add User page did not load successfully.");
            test.pass("Add User page displayed successfully.");

            // Step 4: Filling the signup form with invalid details
            signupPage.enterFirstName(firstName);
            signupPage.enterLastName(lastName);
            signupPage.enterEmail(email);
            signupPage.enterPassword(password);
            test.info("Entered invalid user details.");

            // Step 5: Submitting the form
            signupPage.clickSubmit();
            test.info("Clicked Submit button.");

            // Capture error message
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
            String actualMessage = errorElement.getText();

            // Log and validate the error message
            test.info("Captured error message: " + actualMessage);
            Assert.assertTrue(actualMessage.contains(expectedMessage), "Error message not as expected.");
            test.pass("Correct error message displayed: " + actualMessage);

        } catch (Exception e) {
            String path = UtilityClass.screenShot("SignupNegativeTestFailure"); 
            test.fail("Exception occurred: " + e.getMessage());
            test.fail("Screenshot:").addScreenCaptureFromPath(path);
            Assert.fail("Test Failed due to exception: " + e.getMessage());
        }
    }

    @DataProvider(name = "signupData")
    public String[][] signupDataProvider() throws IOException {
        sheetname = "SignUp";
        return readData(); // Assuming readData() method reads data from Excel sheet
    }
}
