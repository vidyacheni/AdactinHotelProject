package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.UserData;

/**
 * Page class for handling the login functionality.
 */
public class LoginPage {
    
    WebDriver driver;

    // Locators for login page elements
    @FindBy(id = "email") 
    private WebElement usernameField;
    
    @FindBy(id = "password") 
    private WebElement passwordField;
    
    @FindBy(id = "submit") 
    private WebElement loginButton;

    // Constructor to initialize the elements on the page
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to log in using valid credentials
    public void login(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username); // Use parameter
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

}

