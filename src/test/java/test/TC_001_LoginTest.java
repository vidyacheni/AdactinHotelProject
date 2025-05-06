package test;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import pages.LoginPage;
import base.ProjectSpecificationMethods;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;

public class TC_001_LoginTest extends ProjectSpecificationMethods {

    @Test(dataProvider = "getLoginData")
    public void loginTest(String username, String password) {
        // Create a new test in ExtentReport
        test = extent.createTest("Login Test for user: " + username);

        // Log starting of the test
        test.info("Starting login test for user: " + username);

        // Perform the login using the data from DataProvider
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        test.info("Entered username: " + username);

        loginPage.enterPassword(password);
        test.info("Entered password.");

        loginPage.clickLogin();
        test.info("Clicked login button.");

        // Assertion to check login success
        boolean isLoggedIn = loginPage.isLogoutButtonDisplayed();
        
        if (isLoggedIn) {
            // Log the success message in Extent Report
            test.pass("Login successful for user: " + username);
            Assert.assertTrue(true, "Login passed for user: " + username);
        } else {
            // Log the failure message in Extent Report
            test.fail("Login failed for user: " + username);
            Assert.fail("Login failed for user: " + username);
        }

        // Logout
        loginPage.clickLogout();
        test.info("Logged out successfully.");
    }

    @DataProvider(name = "getLoginData")
    public String[][] getLoginData() throws IOException {
        return readExcel("Login"); // Using the correct method name as in UtilityClass
    }

    @AfterSuite
    public void tearDown() {
        // Flush the report after tests are completed
        extent.flush();
    }
}
