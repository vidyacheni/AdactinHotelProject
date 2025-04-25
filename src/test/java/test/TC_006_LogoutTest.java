package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.ContactPage;
import pages.LoginPage;

public class TC_006_LogoutTest extends ProjectSpecificationMethods {

    @Test
    public void verifyLogoutFunctionality() {
        testname = "Logout Test";
        testdescription = "Test logout redirects to login";
        testAuthor = "Automation";
        testCategory = "Functional";

        LoginPage login = new LoginPage(driver);
        login.enterEmail("test@test.com");
        login.enterPassword("Testing@123");
        login.clickLogin();

        ContactPage contact = new ContactPage(driver);
        contact.clickLogout();

        Assert.assertTrue(login.isLoginButtonDisplayed(), "Logout failed, login button not visible.");
    }
}
