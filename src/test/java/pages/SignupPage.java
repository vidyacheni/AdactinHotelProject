package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

import org.openqa.selenium.By;

import utils.WaitUtils;

public class SignupPage extends ProjectSpecificationMethods{

    WebDriver driver;

    // Constructor
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElements
    @FindBy(id = "signup")
    private WebElement signupButton;

    @FindBy(xpath = "//h1[text()='Add User']")
    private WebElement addUserHeading;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;

    @FindBy(id = "error") // Update this if your app uses a different ID or class
    private WebElement responseMessage;

    // Actions
    public boolean isSignupButtonVisible() {
        return signupButton.isDisplayed();
    }

    public boolean isSignupButtonClickable() {
        return signupButton.isEnabled();
    }

    public void clickSignupButton() {
        signupButton.click();
    }

    public boolean isAddUserPageDisplayed() {
        return WaitUtils.waitForElementVisible(driver, By.xpath("//h1[text()='Add User']"), 5) != null;
    }

    public void enterFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    
    
    
    public boolean isContactListPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "Contact List"));
    }



   

    	public String getResponseMessage() {
    	    try {
    	        // First attempt to get the message using the more specific error ID
    	        WebElement messageElement = driver.findElement(By.xpath("//*[@id='error']"));
    	        if (messageElement.isDisplayed()) {
    	            return messageElement.getText();
    	        }

    	        // Fallback to the more general alert message if the specific error element is not found
    	        messageElement = driver.findElement(By.xpath("//div[contains(@class, 'alert')]"));
    	        if (messageElement.isDisplayed()) {
    	            return messageElement.getText();
    	        }

    	        // If no message is found, return an empty string
    	        return "";

    	    } catch (Exception e) {
    	        return "";  // Return empty string in case of any failure to locate the element
    	    }
    	}


    
    
}


