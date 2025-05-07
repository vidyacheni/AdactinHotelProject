package base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import org.apache.commons.io.FileUtils;

import utils.UtilityClass;

public class ProjectSpecificationMethods extends UtilityClass {

    // Instance for managing the overall Extent report
    protected static ExtentReports extent;

    // Instance for logging individual test details
    protected static ExtentTest test;

    // Screenshot storage folder
    String screenshotFolder = "test-output/screenshots/";

    /**
     * Initializes ExtentReports before any test runs.
     * Generates a single HTML report for the entire suite.
     */
    @BeforeSuite
    public void reportInitialization() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("src/test/resources/report/AdactinHotelReport.html");
        reporter.config().setReportName("Adactin Hotel Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        System.out.println("ExtentReports initialized.");
    }

    /**
     * Launches the browser and navigates to the application URL before each test method.
     * Values are supplied from the TestNG XML via parameters.
     *
     * @param browser Name of the browser (e.g., chrome, firefox, edge)
     * @param url     URL of the Adactin hotel booking site
     */
    @Parameters({ "browser", "url" })
    @BeforeMethod
    public void launchingAndLoadingURL(String browser, String url) {
    	System.out.println("BeforeMethod: launching browser");
        launchBrowser(browser, url);
    }

    /**
     * Closes the browser after each test method.
     */
    @AfterMethod
    public void closeBrowserOnly() {
        if (driver != null) {
            driver.quit(); // Closes all browser windows and ends the session
            test.info("Browser closed.");
        }
    }





    /**
     * Capture screenshot during test failures or specific scenarios
     * 
     * @param fileName The name of the screenshot file
     * @return The path to the captured screenshot
     */
    public String captureScreenshot(String fileName) {
        WebDriver driver = getDriver(); // Get the current WebDriver instance
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        
        // Absolute path for storing screenshots
        String screenshotFolder = System.getProperty("user.dir") + "/test-output/screenshots/";
        File directory = new File(screenshotFolder);
        if (!directory.exists()) {
            directory.mkdir(); // Create the directory if it doesn't exist
        }

        // Screenshot file name with current time to avoid duplicates
        String path = screenshotFolder + fileName + "_" + System.currentTimeMillis() + ".png";
        File dest = new File(path);

        try {
            FileUtils.copyFile(src, dest); // Save the screenshot
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
    
    
    

    
    public void clickLogout() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
            logout.click();
            test.info("Clicked on Logout successfully.");
        } catch (Exception e) {
            test.fail("Logout failed: " + e.getMessage());
        }
    }



    /**
     * Flushes and finalizes the ExtentReports after all tests are executed.
     */
   
}
