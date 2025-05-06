package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.UtilityClass;

public class LoginPage extends UtilityClass {

    // Constructor
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this); // Initialize PageFactory elements
    }

    // WebElements using @FindBy
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;
   
    @FindBy(linkText = "Logout")
    private WebElement logoutButton;


    // Actions
    public void enterUsername(String username) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }


    public void enterPassword(String password) {
        passwordInput.clear(); // Clear any pre-filled text
        passwordInput.sendKeys(password); // Enter password
    }

    public void clickLogin() {
        loginButton.click(); // Click login button
    }

    public boolean isLogoutButtonDisplayed() 
    {
        try 
        {
            return logoutButton.isDisplayed();
        } catch (Exception e) 
        {
            return false;
        }
    }



    public void clickLogout() {
        logoutButton.click();
    }


    public boolean isLoginButtonDisplayed() {
        try {
            return loginButton.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

}
