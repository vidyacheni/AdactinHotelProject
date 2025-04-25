package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.LoginPage;
import pages.ContactPage;

public class TC_002_LoginTest extends ProjectSpecificationMethods {

    @Test
    public void validLoginTest() {
        testname = "Valid Login Test";
        testdescription = "Test login with valid credentials";
        testAuthor = "Automation";
        testCategory = "Functional";

        LoginPage login = new LoginPage(driver);
        login.enterEmail("test@test.com");
        login.enterPassword("Testing@123");
        login.clickLogin();

        ContactPage home = new ContactPage(driver);
        Assert.assertTrue(home.isLogoutVisible(), "Login failed - Logout not visible.");
    }
}
