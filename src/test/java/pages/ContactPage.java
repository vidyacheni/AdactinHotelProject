package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
    private WebDriver driver;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "add-contact")
    private WebElement addContactButton;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "birthdate")
    private WebElement birthdateInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//button[text()='Logout']")
    private WebElement logoutButton;

    public void clickAddContact() {
        addContactButton.click();
    }

    public void fillContactDetails(String fName, String lName, String bDate, String email, String phone) {
        firstNameInput.sendKeys(fName);
        lastNameInput.sendKeys(lName);
        birthdateInput.sendKeys(bDate);
        emailInput.sendKeys(email);
        phoneInput.sendKeys(phone);
    }

    public void saveContact() {
        saveButton.click();
    }

    public boolean isLogoutVisible() {
        return logoutButton.isDisplayed();
    }

    public void clickLogout() {
        logoutButton.click();
    }
}
