package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Page Object class for Contact Details page with proper waits.
 */
public class ContactDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public ContactDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators for elements
    @FindBy(id = "add-contact")
    private WebElement addContactButton;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;
    
    @FindBy(id = "streetAddress1")
    private WebElement streetAddress1Field;

    @FindBy(id = "streetAddress2")
    private WebElement streetAddress2Field;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "stateProvince")
    private WebElement stateProvinceField;

    @FindBy(id = "postalCode")
    private WebElement postalCodeField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "birthdate")
    private WebElement birthdateInput;
    
    @FindBy(id = "error")
    private WebElement birthdateErrorElement;


    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;
    
    
    @FindBy(xpath = "//div[contains(@class, 'error') or contains(text(),'duplicate')]")
    private WebElement errorMessageElement;
    
    
//**********************************************************************************************************************************************
    
    //after adding contact ,the next page elements
    
 // First Name span after adding
    @FindBy(xpath = "/html/body/div/div/table/tr[1]/td[2]")
    private WebElement contactFullNameElement;
    
//**************************************************************************************************************************************************
    // elements used in ContactSortTest
    
    @FindBy(xpath = "//table[@id='contactsList']//tr/td[2]")
    private List<WebElement> contactsList;
    
 //***************************************************************************************************************************************************
    //elements for Phone Number  Extension Test
    
 // Locator for the phone number field
    @FindBy(id = "phone")
    private WebElement phoneNumberField;

    // Locator for the phone number text displayed on the contact details page
    @FindBy(xpath = "/html/body/div/div/table/tr[3]/td[5]")
    private WebElement phoneNumberText;
    
    
    // Methods

    public String getErrorMessage() {
        WaitUtils.waitForElementVisible(driver, errorMessageElement, 5);
        return errorMessageElement.getText();
    }

   

    public void clickAddContact() {
        wait.until(ExpectedConditions.elementToBeClickable(addContactButton)).click();
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOf(phoneInput));
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void enterBirthdate(String birthdate) {
        wait.until(ExpectedConditions.visibilityOf(birthdateInput));
        birthdateInput.clear();
        birthdateInput.sendKeys(birthdate);
    }
    
    public void enterCountry(String country) {
        // Wait for the element to be visible and then interact with it
        WebElement countryElement = WaitUtils.waitForElementVisible(driver, countryField, 5);
        
        // Clear the existing text (if any) and then enter the new country name
        countryElement.clear();
        countryElement.sendKeys(country);
    }

    public void enterCity(String city) {
        // Wait for the city field to be visible and then interact with it
        WebElement cityElement = WaitUtils.waitForElementVisible(driver, cityField, 5); 
        
        // Clear the existing text and enter the new city name
        cityElement.clear();
        cityElement.sendKeys(city);
    }



    public void submitContact() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    /**
     * Verify if contact is present on Contact List Page.
     */
    public boolean isContactPresent(String firstName, String lastName) 
    {
        String fullName = firstName + " " + lastName;
        try 
        {
            WebElement contactElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[text()='" + fullName + "']")));
            return contactElement.isDisplayed();
        } catch (Exception e) 
        {
            return false;
        }
    }
    
    
    public boolean isBirthdateFormatErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(birthdateErrorElement));
            String errorText = birthdateErrorElement.getText();
            return errorText != null && !errorText.trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    
public void waitForPageToLoad() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
    try {
        // Assuming there's a unique element that appears after the page loads, like a submit button or contact list
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contactList"))); // Example selector
    } catch (Exception e) {
        System.out.println("Page did not load within timeout: " + e.getMessage());
    }
}   


// methods for the page after adding the contacts

public String getFullNameText() {
    return contactFullNameElement.getText().trim();
}

public void waitForContactDetailsPage() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(contactFullNameElement));
    
}


//methods for ContactSortTest

public void waitForContactsListToUpdate() {
    // Wait for the contacts list to be updated
    new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfAllElements(contactsList));
}
// Method to get the list of all contact names (Full Name: FirstName LastName)
public List<String> getContactsList() {
    List<String> fullNames = new ArrayList<>();
    for (WebElement contact : contactsList) {
        fullNames.add(contact.getText().trim());
    }
    return fullNames;
}
// methods for Phone Number extension Test

public void enterPhoneNumber(String phoneNumber) {
    phoneNumberField.clear();
    phoneNumberField.sendKeys(phoneNumber);

}
public String getPhoneNumberText() {
    return phoneNumberText.getText();
}

}