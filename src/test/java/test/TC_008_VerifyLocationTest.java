package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HotelBookingPage;
import pages.LoginPage;

public class TC_008_VerifyLocationTest extends ProjectSpecificationMethods {

    @Test(dataProvider = "BookingData")
    public void verifyLocationOnSelectHotelPage(String username, String password, String location,
                                                String hotel, String roomType, String noOfRooms,
                                                String checkInDate, String checkOutDate,
                                                String adultsPerRoom, String childrenPerRoom) {

        test = extent.createTest("TC_008_VerifyLocationTest");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        test.info("Logged in successfully with user: " + username);

        HotelBookingPage bookingPage = new HotelBookingPage(driver);
        bookingPage.selectLocation(location);
        bookingPage.selectHotel(hotel);
        bookingPage.selectRoomType(roomType);
        bookingPage.selectNoOfRooms(noOfRooms);
        bookingPage.enterCheckInDate(checkInDate);
        bookingPage.enterCheckOutDate(checkOutDate);
        bookingPage.selectAdultsPerRoom(adultsPerRoom);
        bookingPage.selectChildrenPerRoom(childrenPerRoom);
        bookingPage.clickSearch();
        test.info("Hotel search submitted with location: " + location);

        String displayedLocation = bookingPage.getSelectedHotelLocation();
        Assert.assertEquals(displayedLocation.trim(), location.trim(), "Mismatch in selected and displayed location");
        test.pass("Location matched: " + displayedLocation);
    }

    @DataProvider(name = "BookingData")
    public String[][] BookingData() throws IOException {
        return readExcel("Sheet3"); // your Excel data setup
    }
}
