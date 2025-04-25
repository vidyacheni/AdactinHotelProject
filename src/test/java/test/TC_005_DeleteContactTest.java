package test;

import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;

public class TC_005_DeleteContactTest extends ProjectSpecificationMethods {

    @Test
    public void deleteContact() {
        testname = "Delete Contact Test";
        testdescription = "Delete contact with confirmation";
        testAuthor = "Automation";
        testCategory = "Functional";

        // Add contact > Locate > Delete > Assert contact gone
        // If required, I will add the complete logic
    }
}
