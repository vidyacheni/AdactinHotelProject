package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.MediaEntityBuilder;

import base.ProjectSpecificationMethods;

public class Listener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        Object testClassInstance = result.getInstance();

        if (testClassInstance instanceof ProjectSpecificationMethods) {
            ProjectSpecificationMethods base = (ProjectSpecificationMethods) testClassInstance;
            WebDriver driver = base.driver;

            String screenshotPath = base.captureScreenshot(result.getMethod().getMethodName());
			test.get().fail("Screenshot of failure:",
			        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else {
            test.get().warning("Test class does not extend ProjectSpecificationMethods. Screenshot skipped.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();  // Very important to generate the final report
    }
}
