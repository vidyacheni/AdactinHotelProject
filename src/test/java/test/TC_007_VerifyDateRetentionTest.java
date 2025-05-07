package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HotelBookingPage;
import pages.LoginPage;

public class TC_007_VerifyDateRetentionTest extends ProjectSpecificationMethods {

    @Test(dataProvider = "BookingData")
    public void validateCheckInAndOutDatesRetained(String username, String password, String location, 
                                                   String hotel, String roomType, String noOfRooms, 
                                                   String checkInDate, String checkOutDate) 
    {
        System.out.println("Test TC_105_VerifyDateRetainedTest started");
        test = extent.createTest("TC_007_VerifyDateRetentionTest");

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
        test.info("Searched hotel with given check-in/out dates");

        // Step 3: Validation on "Select Hotel" Page
        String actualCheckInDate = booking.getDisplayedCheckInDate();
        String actualCheckOutDate = booking.getDisplayedCheckOutDate();

        boolean checkInMatch = actualCheckInDate.equals(checkInDate);
        boolean checkOutMatch = actualCheckOutDate.equals(checkOutDate);

        if (checkInMatch && checkOutMatch) {
        	test.info("Expected: " + checkInDate + "/" + checkOutDate + 
                    ", Actual: " + actualCheckInDate + "/" + actualCheckOutDate);
            test.pass("Check-in and check-out dates retained correctly on Select Hotel page.");
            Assert.assertTrue(true);
        } else {
            String screenshotPath = captureScreenshot("TC_105_DateRetain_Failed");
            test.fail("Date mismatch on Select Hotel page.",
                com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            test.info("Expected: " + checkInDate + "/" + checkOutDate + 
                      ", Actual: " + actualCheckInDate + "/" + actualCheckOutDate);
            Assert.fail("Check-in/check-out dates do not match.");
        }
    }

    @DataProvider(name = "BookingData")
    public String[][] fetchBookingData() throws IOException {
        return readExcel("Sheet1");
    }
}
