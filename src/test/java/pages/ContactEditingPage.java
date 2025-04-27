package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import utils.UtilityClass;  // Assuming you have this utility class to handle waits

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
    
    public void clickReturnToContactList() {
        returnToContactListButton.click();
    }
}
