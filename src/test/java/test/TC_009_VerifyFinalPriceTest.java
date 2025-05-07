package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HotelBookingPage;
import pages.LoginPage;

public class TC_009_VerifyFinalPriceTest extends ProjectSpecificationMethods {

    @Test(dataProvider = "FinalPriceData")
    public void verifyFinalBilledPrice(String username, String password, String location,
                                       String hotel, String roomType, String noOfRooms,
                                       String checkInDate, String checkOutDate,
                                       String adultsPerRoom, String childrenPerRoom,
                                       String expectedTotalPrice, String expectedFinalPrice) {

        test = extent.createTest("TC_009_VerifyFinalPriceTest");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        test.info("Logged in as: " + username);

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
        test.info("Hotel search submitted");

        bookingPage.selectFirstHotel();
        //bookingPage.clickContinue();

        double totalPrice = bookingPage.getTotalPrice();  // from UI field
        double actualFinalPrice = bookingPage.getFinalBilledPrice(); // from UI field
        double expectedFinal = Double.parseDouble(expectedFinalPrice);

        test.info("UI Total Price: " + totalPrice);
        test.info("UI Final Billed Price: " + actualFinalPrice);
        test.info("Expected Final Billed Price: " + expectedFinal);

        Assert.assertEquals(actualFinalPrice, expectedFinal, "Final price mismatch");
        test.pass("Final Billed Price verified successfully.");
    }

    @DataProvider(name = "FinalPriceData")
    public String[][] FinalPriceData() throws IOException {
        return readExcel("Sheet4");  
    }
}
