package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.util.Assert;

import utils.UtilityClass;

public class HotelBookingPage extends UtilityClass {
    // Constructor
    public HotelBookingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    // WebElements using @FindBy
    @FindBy(id = "location")
    private WebElement locationDropdown;

    @FindBy(id = "hotels")
    private WebElement hotelsDropdown;

    @FindBy(id = "room_type")
    private WebElement roomTypeDropdown;

    @FindBy(id = "room_nos")
    private WebElement numberOfRoomsDropdown;

    @FindBy(id = "datepick_in")
    private WebElement checkInDateInput;

    @FindBy(id = "datepick_out")
    private WebElement checkOutDateInput;

    @FindBy(xpath="//*[@id='Submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[contains(text(),'Check-In Date shall be before than Check-Out Date')]")
    private WebElement dateErrorMessage;
    
    @FindBy(id = "arr_date_0")  // Appears again to read the displayed date
    private WebElement displayedCheckInDate;

    @FindBy(id = "dep_date_0")
    private WebElement displayedCheckOutDate;
    
    @FindBy(xpath = "//input[@id='location_0']") // Adjust this if not correct
    private WebElement selectedLocationElement;
    
    // Locators for Adults per Room dropdown
    @FindBy(id = "adult_room")
    private WebElement adultsPerRoomDropdown;

    // Locators for Children per Room dropdown
    @FindBy(id = "child_room")
    private WebElement childrenPerRoomDropdown;
    
    @FindBy(id = "radiobutton_0")
    private WebElement radioButtonFirstHotel;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "price_night_0")
    private WebElement totalPriceField;

    @FindBy(id = "total_price_0")
    private WebElement finalBilledPriceField;
    
    @FindBy(id = "order_no")
    private WebElement orderNumberField;

    @FindBy(id = "first_name")
    private WebElement firstNameField;

    @FindBy(id = "last_name")
    private WebElement lastNameField;

    @FindBy(id = "address")
    private WebElement billingAddressField;

    @FindBy(id = "cc_num")
    private WebElement creditCardField;

    @FindBy(id = "cc_type")
    private WebElement cardTypeDropdown;

    @FindBy(id = "cc_exp_month")
    private WebElement expMonthDropdown;

    @FindBy(id = "cc_exp_year")
    private WebElement expYearDropdown;

    @FindBy(id = "cc_cvv")
    private WebElement cvvField;

    @FindBy(id = "book_now")
    private WebElement bookNowButton;
    
      
    
    public String getSelectedHotelLocation() {
        return selectedLocationElement.getAttribute("value"); // Or getText(), depending on HTML
    }

    public void selectAdultsPerRoom(String adults) {
        adultsPerRoomDropdown.click();
        adultsPerRoomDropdown.sendKeys(adults); // You can also use Select class for dropdowns
    }

    // Methods to select Children per Room
    public void selectChildrenPerRoom(String children) {
        childrenPerRoomDropdown.click();
        childrenPerRoomDropdown.sendKeys(children); // You can also use Select class for dropdowns
    }

    
       // Actions
    public void selectLocation(String location) {
        selectDropdownByVisibleText(locationDropdown, location);
    }

    public void selectHotel(String hotel) {
        selectDropdownByVisibleText(hotelsDropdown, hotel);
    }

    public void selectRoomType(String roomType) {
        selectDropdownByVisibleText(roomTypeDropdown, roomType);
    }

    public void selectDropdownByVisibleText(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    public void selectNoOfRooms(String noOfRooms) {
        System.out.println("Excel input: " + noOfRooms);
        selectDropdownByVisibleText(numberOfRoomsDropdown, noOfRooms);
    }
    public void enterCheckInDate(String date) {
        WebElement checkIn = driver.findElement(By.id("datepick_in"));
        
        // Clear the field before entering data
        checkIn.clear();
        
        // Enter the date
        checkIn.sendKeys(date);
        
        // Trigger any form validation that might be associated with the field
        checkIn.sendKeys(Keys.TAB);
     
        // Manually verify by inspecting the field in the browser or logging the action
        System.out.println("Entered check-in date: " + date);
    }
    public void enterCheckOutDate(String date) {
        WebElement checkOut = driver.findElement(By.id("datepick_out"));
       
        // Clear the field before entering data
        checkOut.clear();
        
        // Enter the date
        checkOut.sendKeys(date);
   
        // Trigger any form validation that might be associated with the field
        checkOut.sendKeys(Keys.TAB); 
        System.out.println("Entered check-out date: " + checkOut.getText());
    }
    public void clickSearch() {
        WebElement searchButton = driver.findElement(By.id("Submit"));
        
        // If it's a form element, you can use submit()
        searchButton.click();
    }
    public void clickLogout() {
        // Locate the logout button by the XPath
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/a[4]"));
        
        // Click on the logout button
        logoutButton.click();
    }
    
    public String getDisplayedCheckInDate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(displayedCheckInDate));
        return displayedCheckInDate.getAttribute("value");
    }

  

    public String getDisplayedCheckOutDate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(displayedCheckOutDate));
        return displayedCheckOutDate.getAttribute("value");
    }


    
    
    
    
    public boolean isDateErrorDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.visibilityOf(dateErrorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
    /**
     * Select the first hotel radio button
     */
    public void selectFirstHotel() {
        radioButtonFirstHotel.click();
    }

    /**
     * Click the Continue button
     */
    public void clickContinue() {
        continueButton.click();
    }

    /**
     * Get the total price (before adding 10%)
     */
    public double getTotalPrice() {
        String price = totalPriceField.getAttribute("value");
        return extractNumber(price);
    }

    /**
     * Get the final billed price (including 10% addition)
     */
    public double getFinalBilledPrice() {
        String price = finalBilledPriceField.getAttribute("value");
        return extractNumber(price);
    }
 
    private double extractNumber(String priceText) {
        // Remove all non-digit and non-decimal characters
        String numeric = priceText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(numeric);
    }
    
  
 // Booking methods
    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterBillingAddress(String address) {
        billingAddressField.sendKeys(address);
    }

    public void enterCreditCardNo(String cardNo) {
        creditCardField.sendKeys(cardNo);
    }

    public void selectCreditCardType(String type) {
        selectDropDownUsingText(cardTypeDropdown, type);
    }

    public void selectExpiryMonth(String month) {
        selectDropDownUsingText(expMonthDropdown, month);
    }

    public void selectExpiryYear(String year) {
        selectDropDownUsingText(expYearDropdown, year);
    }

    public void enterCVVNumber(String cvv) {
        cvvField.sendKeys(cvv);
    }

    public void clickBookNow() {
        bookNowButton.click();
    }
    
    
    public void selectDropDownUsingText(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }
    
    public String getOrderNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> !orderNumberField.getAttribute("value").isEmpty());
        return orderNumberField.getAttribute("value");
    }

    
}
