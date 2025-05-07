package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HotelBookingPage;
import pages.LoginPage;


public class TC_010_VerifyOrderNumberTest extends ProjectSpecificationMethods {

    @Test(dataProvider = "OrderData")
    public void verifyOrderNumberGenerated(String username, String password, String location,
                                           String hotel, String roomType, String noOfRooms,
                                           String checkInDate, String checkOutDate,
                                           String adultsPerRoom, String childrenPerRoom) {

        test = extent.createTest("TC_010_VerifyOrderNumberTest");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        test.info("Logged in successfully");

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
        test.info("Searched hotel with provided data");

        bookingPage.selectFirstHotel();
        bookingPage.clickContinue();

        bookingPage.enterFirstName("John");
        bookingPage.enterLastName("Doe");
        bookingPage.enterBillingAddress("123 Example St");
        bookingPage.enterCreditCardNo("4111111111111111");
        bookingPage.selectCreditCardType("VISA");
        bookingPage.selectExpiryMonth("May");
        bookingPage.selectExpiryYear("2026");
        bookingPage.enterCVVNumber("123");
        bookingPage.clickBookNow();

        String orderNumber = bookingPage.getOrderNumber();


        test.info("Order Number generated: " + orderNumber);
        Assert.assertNotNull(orderNumber, "Order Number is null!");
        Assert.assertFalse(orderNumber.trim().isEmpty(), "Order Number is empty!");
        test.pass("Order number successfully generated: " + orderNumber);
    }

    @DataProvider(name = "OrderData")
    public String[][] OrderData() throws IOException {
        return readExcel("Sheet5"); // Create test data in Excel Sheet5
    }
}
