package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HotelBookingPage;
import pages.LoginPage;

public class TC_006_VerifyPastDateTest extends ProjectSpecificationMethods 
{

    @Test(dataProvider = "BookingPastDate")
    public void validateDateFieldErrorForPastCheckInDate(String username, String password, String location, 
                                                         String hotel, String roomType, String noOfRooms, 
                                                         String checkInDate, String checkOutDate) 
    {
    	
    	System.out.println("Test method started");
        // Create ExtentReport test instance
        test = extent.createTest("TC_006_VerifyPastDateTest");

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
        if (booking.isDateErrorDisplayed()) {
            test.pass("Correct error message displayed for invalid past check-in date.");
            Assert.assertTrue(true, "Test passed: Error shown for invalid past check-in date.");
        } else {
            String screenshotPath = captureScreenshot("InvalidPastDateTest_Failed");
            test.fail("No error message displayed for invalid past check-in date.", 
                      com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            Assert.fail("Test failed: No error message shown for invalid past check-in date.");
        }

    }

    @DataProvider(name = "BookingPastDate")
    public String[][] BookingPastDate() throws IOException {
    	System.out.println("DataProvider called");
        return readExcel("Sheet1");
    }
}
