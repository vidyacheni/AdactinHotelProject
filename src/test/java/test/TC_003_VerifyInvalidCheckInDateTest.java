package test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HotelBookingPage;
import pages.LoginPage;

public class TC_003_VerifyInvalidCheckInDateTest extends ProjectSpecificationMethods {
	
    @Test(dataProvider = "getHotelBookingData")
    public void validateDateFieldError(String username, String password, String location, String hotel,
                                       String roomType, String noOfRooms, String checkInDate, String checkOutDate) {

        // Create ExtentReport test instance
        test = extent.createTest("Invalid Check-In Date Test for user: " + username);

        // Step 1: Login
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        test.info("Entered username: " + username);

        loginPage.enterPassword(password);
        test.info("Entered password");

        loginPage.clickLogin();
        test.info("Clicked Login");

        // Step 2: Hotel Booking
        HotelBookingPage booking = new HotelBookingPage(driver);
        booking.selectLocation(location);
        booking.selectHotel(hotel);
        booking.selectRoomType(roomType);
        booking.selectNoOfRooms(noOfRooms);
        booking.enterCheckInDate(checkInDate);
        booking.enterCheckOutDate(checkOutDate);
        booking.clickSearch();
        test.info("Submitted hotel booking with invalid check-in/check-out dates");

        // Step 3: Validation
        if (booking.isDateErrorDisplayed()) 
        {
            test.pass("Correct error message is displayed for invalid date range");
            Assert.assertTrue(true);
        } else 
        {
            String screenshotPath = captureScreenshot("InvalidDateTest_Failed");
            test.fail("No error message displayed for invalid date", 
                      com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            Assert.fail("No error shown for invalid date");
        }
    }
    @AfterTest
    public void browserQuit() {
        if (driver != null) {
            driver.quit();
            test.info("Browser closed.");
        }
    }
    
    
    @DataProvider(name = "getHotelBookingData")
    public String[][] getHotelBookingData() throws IOException {
        return readExcel("HotelBookingData");
    }
   
}
