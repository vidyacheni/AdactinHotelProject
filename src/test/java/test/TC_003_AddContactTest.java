package test;

import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.ContactPage;
import pages.LoginPage;

public class TC_003_AddContactTest extends ProjectSpecificationMethods {

    @Test
    public void addNewContact() {
        testname = "Add Contact Test";
        testdescription = "Add contact with all fields";
        testAuthor = "Automation";
        testCategory = "Functional";

        LoginPage login = new LoginPage(driver);
        login.enterEmail("test@test.com");
        login.enterPassword("Testing@123");
        login.clickLogin();

        ContactPage contact = new ContactPage(driver);
        contact.clickAddContact();
        contact.fillContactDetails("Alex", "Brown", "01/01/2000", "alex@example.com", "+911234567890");
        contact.saveContact();
    }
}

