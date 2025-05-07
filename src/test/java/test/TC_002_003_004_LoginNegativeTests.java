package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import base.ProjectSpecificationMethods;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class TC_002_003_004_LoginNegativeTests extends ProjectSpecificationMethods {

    @Test
    public void TC_Login_001_EmptyUsernameAndPassword() {
        // Start the report node
        test = extent.createTest("TC_Login_002 - Empty Username & Password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        // If logout button is present, login succeeded unexpectedly
        if (loginPage.isLogoutButtonDisplayed()) {
            String path = captureFailureScreenshot("TC_Login_001");
            test.fail("Login succeeded with empty credentials")
                .addScreenCaptureFromPath(path);
            Assert.fail("FAIL: Login succeeded with empty credentials");
        }

        // Otherwise the login page is still shown → pass
        test.pass("Login button still visible, invalid credentials correctly rejected");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    public void TC_Login_003_OnlyPasswordEntered() {
        test = extent.createTest("TC_Login_003 - Only Password Entered");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("");
        loginPage.enterPassword("Discord@123");
        loginPage.clickLogin();

        if (loginPage.isLogoutButtonDisplayed()) {
            String path = captureFailureScreenshot("TC_Login_003");
            test.fail("Login succeeded with password only")
                .addScreenCaptureFromPath(path);
            Assert.fail("FAIL: Login succeeded with password only");
        }

        test.pass("Login button still visible, missing username correctly rejected");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    public void TC_Login_007_SpecialCharacters() {
        test = extent.createTest("TC_Login_004 - Special Characters");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("!@#$%");
        loginPage.enterPassword("%^&*()");
        loginPage.clickLogin();

        if (loginPage.isLogoutButtonDisplayed()) {
            String path = captureFailureScreenshot("TC_Login_007");
            test.fail("Login succeeded with special characters")
                .addScreenCaptureFromPath(path);
            Assert.fail("FAIL: Login succeeded with special characters");
        }

        test.pass("Login button still visible, special-character credentials correctly rejected");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    /**
     * Captures a screenshot on failure and returns the file path.
     */
    private String captureFailureScreenshot(String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String dir = System.getProperty("user.dir") + "/src/test/resources/screenshots/";
            new File(dir).mkdirs();
            String path = dir + testName + "_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
