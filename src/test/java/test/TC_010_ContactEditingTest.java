package test;  
import org.testng.Assert; 
import org.testng.annotations.BeforeClass; 
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Test; 
import base.ProjectSpecificationMethods; 
import pages.ContactEditingPage; 
// Import ContactEditingPage instead of ContactDetailsPage 
import pages.LoginPage; 
import utils.UserData;  
public class TC_010_ContactEditingTest extends ProjectSpecificationMethods 
{      
	private LoginPage loginPage;     
	private ContactEditingPage contactEditingPage;  
	// Use ContactEditingPage      
	@BeforeClass     
	public void testDetails() 
	{         
		testname = "Verify Contact Editing";         
		testdescription = "Test that verifies the ability to edit contact details (name, email, phone number, etc.).";         
		testCategory = "Functional";         
		testAuthor = "Vidya Cheni";         
		super.testDetails();     
		}      
	@Test(description = "Test editing existing contact details and page redirection upon contact click")     
	public void editContactDetails() 
	{         
		test.info("Starting test to verify contact editing functionality and page redirection.");          
		// Log in         
		loginPage = new LoginPage(driver);         
		loginPage.login(UserData.getEmail(), UserData.getPassword());         
		test.info("Logged in successfully.");          
		// Navigate to Add Contact         
		contactEditingPage = new ContactEditingPage(driver);  
		// Instantiate ContactEditingPage         
		contactEditingPage.clickAddContact();         
		test.info("Clicked Add Contact button.");          
		// Prepare expected contact details         
		String expectedFirstName = "phone";         
		String expectedLastName = "email";         
		String expectedPhoneNumber = "+919876543210";  // Example phone number with extension         
		String expectedEmail = "email@example.com";  // Example email   
		String initialCity= "Hyderabad";
		// Enter initial contact details         
		contactEditingPage.enterFirstName(expectedFirstName);         
		contactEditingPage.enterLastName(expectedLastName);         
		contactEditingPage.enterPhoneNumber(expectedPhoneNumber);         
		contactEditingPage.enterEmail(expectedEmail);   
		contactEditingPage.enterCity(initialCity);
		test.info("Entered initial contact details.");          // Submit the contact         
		contactEditingPage.submitContact();         
		test.info("Submitted the new contact.");          // Wait for the contact list to update         
		contactEditingPage.waitForContactsListToUpdate();          // Find and click on the newly added contact         
		contactEditingPage.clickContactFromList(expectedFirstName, expectedLastName);         
		test.info("Clicked on the newly added contact.");          // Click on the "Edit Contact" button to allow editing         
		contactEditingPage.clickEditContactButton();         
		test.info("Clicked on Edit Contact button.");
		try {      
			Thread.sleep(5000); // ⏳ Sleep for 3 seconds (3000 milliseconds)             
			} catch (InterruptedException e) 
		{                 e.printStackTrace();             
		}                     
		      
		// Edit contact details         
		String updatedPhoneNumber = "+919999999999";         
		String updatedEmail = "emailnew@example.com";  
		
		contactEditingPage.enterPhoneNumber(updatedPhoneNumber);         
		contactEditingPage.enterEmail(updatedEmail);         
		contactEditingPage.submitContact();  
		
		test.info("Edited contact details and submitted.");                  
		contactEditingPage.clickReturnToContactList();         
		test.info("Returned to contact list page.");                                        
		try {      
			Thread.sleep(5000); // ⏳ Sleep for 3 seconds (3000 milliseconds)             
			} catch (InterruptedException e) 
		{                 e.printStackTrace();             
		}                     
		
		
		String expectedCountry="";
		String actualPhoneNumber = contactEditingPage.getPhoneNumberText();         
		String actualEmail = contactEditingPage.getEmailText();          // Assert that the updated details are correct  
		String actualCity = contactEditingPage.getCityText();
		String actualCountry=contactEditingPage.getCountryText();
		
		
		Assert.assertEquals(actualPhoneNumber, updatedPhoneNumber, "Phone number not updated correctly.");         
		Assert.assertEquals(actualEmail, updatedEmail, "Email not updated correctly.");    
		test.pass("Contact details were successfully edited."); 
		 Assert.assertEquals(actualCity, initialCity, "unrelated field - City is unchanged.");
		 test.pass("changes made to one field don't affect other unrelated fields."); 
		 Assert.assertEquals(actualCountry, expectedCountry, "App allows empty fields.");
		 test.pass("the app allows saving empty fields."); 
		 
		
		  
		} 
	} 
		