package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utils.UtilityClass;  // Assuming you have this utility class to handle waits
import utils.WaitUtils;

public class ContactEditingPage {

    private WebDriver driver;

    // Locators for elements
    @FindBy(id = "add-contact")
    private WebElement addContactButton;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "phone")
    private WebElement phoneNumberField;

    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(id = "city")
    private WebElement cityField;
    
    

    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//table//tr[1]/td[2]")
    private WebElement contactNameField; // For contact name in contact list

    @FindBy(id = "edit-contact")
    private WebElement editContactButton;

    @FindBy(xpath = "/html/body/div/div/table/tr[6]/td[5]")
    private WebElement phoneNumberText;


    @FindBy(xpath = "/html/body/div/div/table/tr[6]/td[4]")
    private WebElement emailText;
    
    
    @FindBy(xpath = "/html/body/div/div/table/tr[6]/td[7]")
    private WebElement CityText;
    
    @FindBy(xpath = "/html/body/div/div/table/tr[6]/td[8]")
    private WebElement CountryText;
    
    @FindBy(id="return")
    private WebElement returnToContactListButton;
    
    // Constructor
    public ContactEditingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to click on Add Contact button
    public void clickAddContact() {
        addContactButton.click();
    }

    // Method to enter first name
    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    // Method to enter last name
    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    // Method to enter phone number
    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
    }

    // Method to enter email
    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Method to submit the contact form
    public void submitContact() {
        submitButton.click();
    }
    
    //***************************************
    
 // City cell in contact list
    @FindBy(how = How.XPATH, using = "/html/body/div/div/table/tr[6]/td[7]")
    private WebElement cityCell;

    // Delete Contact button
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Delete Contact')]")
    private WebElement deleteContactButton;

    // (No need FindBy for alert, handled separately)

    // Table rows for verifying city names (will collect dynamically)
    @FindBy(how = How.XPATH, using = "//table//tr/td[7]")
    private List<WebElement> cityColumnCells;
    
    
    //************************************************************************************
 // Logout Button on Contact List page
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Logout')]")
    private WebElement logoutButton;

    

    // Wait for the contacts list to update after adding a new contact
    public void waitForContactsListToUpdate() {
        UtilityClass.waitForElementToBeVisible(driver, By.xpath("//table//tr/td[2]"));  // Adjust the XPath as needed
    }

    // Method to click on the contact from the contact list (based on first and last name)
    public void clickContactFromList(String firstName, String lastName) {
        String contactXpath = "//td[contains(text(),'" + firstName + " " + lastName + "')]";
        WebElement contact = driver.findElement(By.xpath(contactXpath));
        contact.click();
    }

    // Method to click the "Edit Contact" button
    public void clickEditContactButton() {
        editContactButton.click();
    }

    // Wait for the contact details page to refresh after editing
    public void waitForContactDetailsPage() {
        UtilityClass.waitForElementToBeVisible(driver, By.xpath("//div[@class='contact-details']"));
    }

    // Get the phone number text from the contact details page
    public String getPhoneNumberText() {
        return phoneNumberText.getText();
    }

    // Get the email text from the contact details page
    public String getEmailText() {
        return emailText.getText();
        
    }
    
    public void enterCity(String city) {
        // Wait for the city field to be visible and then interact with it
        WebElement cityElement = WaitUtils.waitForElementVisible(driver, cityField, 5); 
        
        // Clear the existing text and enter the new city name
        cityElement.clear();
        cityElement.sendKeys(city);
    }
    
    
    
    public void clickReturnToContactList() {
        returnToContactListButton.click();
    }

	public String getCityText() {
		// TODO Auto-generated method stub
		 return CityText.getText();
		
	}

	public String getCountryText() {
		return CountryText.getText();
	}
	
	
	//*********************************************************************************************************************
	
	// Click on the City Cell
	public void clickOnCityCell() {
	    cityCell.click();
	}

	// Click Delete Contact Button
	public void clickDeleteContactButton() {
	    deleteContactButton.click();
	}

	// Check if an Alert is present
	public boolean isAlertPresent() {
	    try {
	        driver.switchTo().alert();
	        return true;
	    } catch (NoAlertPresentException e) {
	        return false;
	    }
	}

	// Accept the Alert
	public void acceptAlert() {
	    try {
	        driver.switchTo().alert().accept();
	    } catch (NoAlertPresentException e) {
	        e.printStackTrace();
	    }
	}

	// Check if a City Name is still present in Contact List
	public boolean isCityPresentInList(String cityName) {
	    for (WebElement cityCell : cityColumnCells) {
	        if (cityCell.getText().equalsIgnoreCase(cityName)) {
	            return true;
	        }
	    }
	    return false;
	}

	//************************************************************************************************************
	// Check if Logout Button is displayed
	public boolean isLogoutButtonDisplayed() {
	    return logoutButton.isDisplayed();
	}

	// Click on Logout Button
	public void clickLogoutButton() {
	    logoutButton.click();
	}
 
	// Verify if redirected to Login Page
	// (Optional: you can verify by checking presence of login fields/buttons after logout)
	public boolean isLoginPageDisplayed() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for 10 seconds
	    try {
	        // Wait for the login button to be visible
	    	WebElement loginButton = driver.findElement(By.xpath("//*[@id='submit']"));
	        
	        return loginButton.isDisplayed();  // Return true if login button is visible
	    } catch (Exception e) {
	        return false;  // Return false if the element is not found within the timeout
	    }
	}

	}

	
